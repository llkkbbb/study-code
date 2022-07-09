# Java에서 파일을 읽는 여러가지 방법

## 목표

- 클래스 경로, URL에서 파일을 로드하는 방법
- BufferedReader, Scanner, StreamTokenizer, DataInputStream, SequenceInputStream 및 FileChannel 사용하여 읽는 방법
- UTF-8로 인코딩된 파일을 읽는 방법

---

## 입력 파일 읽는 방법

- 읽을 파일을 준비한다. test.txt

```java
Hello, World!
```

- 파일을 읽는 메서드를 준비한다.

```java
public class HelperFactory {
    public static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
						// 다음 토큰줄이 null일 때 까지 순회
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }
}
```

파일을 읽는 InputStream을 인자로 받는 메서드를 작성한다.

- 테스트

```java
@Test
@DisplayName("입력파일 테스트")
void readFromInputStream() throws IOException {
// 파일을 읽어 InputStream에 저장
    FileInputStream fileInputStream = new FileInputStream("test.txt");

// 위에서 만든 메서드를 사용하여 읽은 파일 내용을 문자열로 변환
    String result = HelperFactory.readFromInputStream(fileInputStream);

// 파일 내용 검증
assertThat(result).isEqualTo("Hello, World!\n");
}
```

## 클래스 경로에서 파일 읽기

예) src/main/resources 아래에 있는 파일을 읽음

```java
@Test
@DisplayName("클래스 경로에서 파일 읽기")
void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
   // given
        String expectedFileData = "Hello, World!\n";

        // when
        Class<HelperFactory> clazz = HelperFactory.class;
        InputStream inputStream = clazz.getResourceAsStream("/test.txt");
        String data = HelperFactory.readFromInputStream(inputStream);

        // then
        assertThat(data).contains(expectedFileData);
}
```

현재 클래스를 사용하고 getResourceAsStream() 메서드를 사용하여 로드 하려고 하는 파일의 절대 경로를 전달한다.

ClassLoader 인스턴스로도 동일하게 진행할 수 있다.

```java
@Test
@DisplayName("클래스 경로에서 파일 읽기1")
void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData1() throws IOException {
    // given
    String expectedFileData = "Hello, World!\n";

    // when
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("test.txt");
    String data = HelperFactory.readFromInputStream(inputStream);

    // then
assertThat(data).contains(expectedFileData);
}
```

getClass().getClassLoader()를 사용하여 ClassLoader를 얻는다.

공통점

- 로드하려고 하는 파일의 경로를 전달

차이점

- ClassLoader 인스턴스에서는 경로가 클래스 경로의 루트에서 시작하여 절대 경로로 처리된다.
- Class 인스턴스에서는 패키지에 상대적일 수 있거나 선행 슬래시로 암시되는 절대 경로일 수 있다.

### 열린 자원은 작업이 끝나면 항상 닫혀 있어야 한다.

```java
@Test
@DisplayName("클래스 경로에서 파일 읽기1")
void readFromInputStream_Exception_Handling(){
    // given
    String expectedFileData = "Hello, World!\n";

    // when
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = null;

    String data = "";
    try (FileInputStream fileInputStream = new FileInputStream(Objects.requireNonNull(classLoader.getResource("test.txt")).getFile())) {
        data = HelperFactory.readFromInputStream(fileInputStream);
    } catch (Exception e) {
        e.printStackTrace();
    }

    // then
assertThat(data).contains(expectedFileData);
}
```

위의 코드는 try-with-resource 를 사용했다. `AutoCloseable` 을 구현한 클래스여야 사용이 가능하다.

자동으로 자원을 닫게 해준다.

## BufferedReader 로 읽기

파일의 내용을 구문 분석하는 방법

```java
@Test
@DisplayName("BufferedReader 로 파일 읽기")
void bufferedReader_Test() throws IOException {
    // given
    String expectedFileData = "Hello, World!";
    String file = "src/test/resources/test.txt";

    // when
    String line = "";
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        line = br.readLine();
    }

    // then
		assertThat(line).isEqualTo(expectedFileData);
}
```

해당 파일의 첫번째 줄을 가져와 내용을 검증한다.

### 작은 용량의 파일 읽기

- 작은 용량이면 Files.readAllLines() 메서드를 사용한다.

```java
@Test
@DisplayName("작은 용량의 파일")
void small_size_file() throws IOException {
    // given
    String expectedFileData = "Hello, World!";
    String file = "src/test/resources/test.txt";
    Path path = Paths.get(file);

    // when
    String line = Files.readAllLines(path).get(0);

    // then
		assertThat(line).isEqualTo(expectedFileData);
}
```

readAllLines() 메서드는 리스트 타입이며 인덱스로 줄 관리를 한다.

### 대용량 파일 읽기

- Files 로 대용량 파일을 읽으려면 BufferedReader를 사용할 수 있다.

```java
@Test
@DisplayName("큰 용량의 파일")
void big_size_file() throws IOException {
    // given
    String expectedFileData = "Hello, World!";
    String file = "src/test/resources/test.txt";
    Path path = Paths.get(file);

    // when
    String line = "";
    try (BufferedReader br = Files.newBufferedReader(path)) {
        line = br.readLine();
    }

    // then
		assertThat(line).isEqualTo(expectedFileData);
}
```

### Files.lines() 로 읽기

jdk 8부터 Files에 lines() 메서드를 제공한다. String 타입의 Stream을 반환하여 자원을 사용한 다음 닫아한다.

```java
@Test
@DisplayName("Files.lines() 를 사용하여 파일 읽기")
void files_lines() throws URISyntaxException, IOException {
    // given
    String expectedFileData = "Hello, World!";
    Path path = Paths.get(getClass().getClassLoader()
            .getResource("test.txt")
            .toURI());

    // when && then
    try (Stream <String> lines = Files.lines(path)) {
        String data = lines.collect(Collectors.joining("\n"));
				assertThat(data).isEqualTo(expectedFileData);
    }
}
```

lines() 메서드는 Path 타입을 파라미터로 받아서 String 타입으로 반환해준다.

### Scanner 로 읽기

```java
@Test
@DisplayName("Scanner 로 읽기")
void scannerRead() throws URISyntaxException, IOException {
    String expectedFileData = "Hello, World!";
    Path path = Paths.get(getClass().getClassLoader()
            .getResource("test.txt")
            .toURI());

    try (Scanner scanner = new Scanner(path);) {
        scanner.useDelimiter(" ");

				assertTrue(scanner.hasNext());
				assertThat("Hello,").isEqualTo(scanner.next());
				assertThat("World!").isEqualTo(scanner.next());
    }
}
```

useDelimiter() 메서드를 사용하여 공백으로 구분했다.

### BufferedReader 와 Scanner 의 차이점을 알아보자.

- BufferedReader 는 입력을 라인으로 읽는다.
    - readLine() 메서드를 사용한다.
- Scanner 는 입력을 토큰으로 읽는다.
    - next() 메서드를 사용한다.
    

입력을 토큰으로 읽는 것보단 한 라인을 바로 읽는 것이 효율적이다. 하지만 상황에 맞게 사용하는 것이 모범적인 프로그래밍이 아닐까 생각한다.

### StreamTokenizer 로 읽기

- 입력 파일을 토큰으로 읽어온다.
- 다음 토큰이 무엇인지 먼저 파악하며 동작
- Tokenizer.ttype 살펴보고 수행
    - tokenizer.nval - 숫자인 경우
    - tokenizer.sval - 문자열인 경우
    

새로운 테스트 파일을 생성하여 테스트 하였다.

test1.txt 생성

```java
Hello 1
```

```java
@Test
@DisplayName("Tokenizer로 읽기")
void streamTokenizer() throws IOException {
    String file = "src/test/resources/test1.txt";

    try (FileReader fileReader = new FileReader(file)) {
        StreamTokenizer streamTokenizer = new StreamTokenizer(fileReader);

        streamTokenizer.nextToken();
        // 스트림의 값이 문자열인 경우
				assertEquals(StreamTokenizer.TT_WORD, streamTokenizer.ttype);
				assertEquals("Hello", streamTokenizer.sval);

        streamTokenizer.nextToken();
        // 스트림의 값이 숫자인 경우
				assertEquals(StreamTokenizer.TT_NUMBER, streamTokenizer.ttype);
				assertEquals(1, streamTokenizer.nval);

        streamTokenizer.nextToken();
        // 스트림의 끝을 읽은 경우
				assertEquals(StreamTokenizer.TT_EOF, streamTokenizer.ttype);
    }
}
```

### DataInputStream 으로 읽기

```java
@Test
@DisplayName("DataInputStream 으로 읽기")
void dataInputStream() throws IOException {
    String expectedValue = "Hello, world!";
    String file ="src/test/resources/test.txt";
    String result = null;

    try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
        int available = dataInputStream.available();
        if (available > 0) {
            byte[] bytes = new byte[available];
            dataInputStream.read(bytes);
            result = new String(bytes);
        }
				assertThat(result).isEqualTo(expectedValue);
    }
}
```

DataInputStream 을 사용하여 바이너리 값과 기본 데이터 유형을 읽을 수 있다.

### FileChannel 로 읽기

- 대용량 파일을 읽는다면 표준 I/O 보다 빠를 수 있다.

```java
@Test
@DisplayName("FileChannelRead 읽기")
void fileChannelRead() throws IOException {
    String expected_value = "Hello, World!";
    String file = "src/test/resources/test.txt";
    RandomAccessFile reader = new RandomAccessFile(file, "r");
    FileChannel channel = reader.getChannel();

    int bufferSize = 1024;
    // 1024 보다 작으면 channel size 를 사용
    if (bufferSize > channel.size()) {
        bufferSize = (int) channel.size();
    }
    // channel size 가 1024 보다 크면 bufferSize 사용
    ByteBuffer buff = ByteBuffer.allocate(bufferSize);
    channel.read(buff);
    buff.flip();

		assertEquals(expected_value, new String(buff.array()));
    channel.close();
    reader.close();
}
```

FileChannel 및 RandomAccessFile 을 사용하여 파일에서 데이터 바이트를 읽는다.

### UTF-8로 인코딩된 파일 읽기

- BufferedReader 를 사용하여 UTF-8로 인코딩된 파일을 읽는 방법

```java
@Test
@DisplayName("UTF-8로 인코딩된 파일을 읽는 방법")
void encoding_file_reader() throws IOException {
    String expectFileData = "안녕";
    String file = "src/test/resources/test3.txt";

    FileReader fileReader = new FileReader(file, StandardCharsets.UTF_8);
    try (BufferedReader br = new BufferedReader(fileReader)) {
        String line = br.readLine();
				assertThat(line).isEqualTo(expectFileData);
    }
}
```

### URL에서 콘텐츠 읽기

```java
@Test
@DisplayName("URL에서 콘텐츠 읽기")
void url_content_read() throws IOException {
    URL url = new URL("/");
    URLConnection urlConnection = url.openConnection();

    InputStream inputStream = urlConnection.getInputStream();
    String data = HelperFactory.readFromInputStream(inputStream);

    System.out.println(data);
}
```

참고 : 

[https://www.baeldung.com/reading-file-in-java](https://www.baeldung.com/reading-file-in-java)