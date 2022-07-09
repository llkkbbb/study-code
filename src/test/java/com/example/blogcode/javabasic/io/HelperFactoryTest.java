package com.example.blogcode.javabasic.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * packageName    : com.example.blogcode.javabasic.io
 * fileName       : HelperFactoryTest
 * author         : tkdwk567@naver.com
 * date           : 2022/06/26
 */

class HelperFactoryTest {

    @Test
    @DisplayName("입력파일 테스트")
    void readFromInputStream(InputStream inputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("test.txt");

        String result = HelperFactory.readFromInputStream(fileInputStream);

        assertThat(result).isEqualTo("Hello, World!\n");
    }

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

    @Test
    @DisplayName("클래스 경로에서 파일 읽기1")
    void readFromInputStream_Exception_Handling() throws IOException {
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

    @Test
    @DisplayName("Files.lines() 를 사용하여 파일 읽기")
    void files_lines() throws URISyntaxException, IOException {
        // given
        String expectedFileData = "Hello, World!";
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("test.txt")
                .toURI());

        // when && then
        try (Stream<String> lines = Files.lines(path)) {
            String data = lines.collect(Collectors.joining("\n"));
            assertThat(data).isEqualTo(expectedFileData);
        }
    }

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

    @Test
    @DisplayName("DataInputStream 으로 읽기")
    void dataInputStream() throws IOException {
        String expectedValue = "Hello, world!";
        String file = "src/test/resources/test.txt";
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

    @Test
    @DisplayName("URL에서 콘텐츠 읽기")
    void url_content_read() throws IOException {
        URL url = new URL("/");
        URLConnection urlConnection = url.openConnection();

        InputStream inputStream = urlConnection.getInputStream();
        String data = HelperFactory.readFromInputStream(inputStream);

        System.out.println(data);
    }
}