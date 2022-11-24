package bzh.ineed.rolandgarros.util;

import bzh.ineed.rolandgarros.model.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVPlayerUtil {
    public static String TYPE = "text/csv";
    public static String[] HEADERS = {"firstname", "lastname"};

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Person> csvToPlayers(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.builder().setSkipHeaderRecord(true).setIgnoreHeaderCase(true).setTrim(true).setHeader(HEADERS).build());

            List<Person> players = new ArrayList<Person>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Person player = new Person(csvRecord.get("firstname"), csvRecord.get("lastname"));
                players.add(player);
            };

            return players;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
