package bzh.ineed.rolandgarros.configuration;

import bzh.ineed.rolandgarros.model.*;
import bzh.ineed.rolandgarros.repository.*;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(
            CountryRepository countryRepository,
            PersonRepository personRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            CourtRepository courtRepository,
            TrainingRepository trainingRepository
    ) {
        return args -> {
            // DELETE ALL
            log.info("[TRAINING] Delete all");
            trainingRepository.deleteAll();

            log.info("[PERSON] Delete all");
            personRepository.deleteAll();

            log.info("[COUNTRY] Delete all");
            countryRepository.deleteAll();

            log.info("[USER] Delete all");
            userRepository.deleteAll();

            log.info("[ROLE] Delete all");
            roleRepository.deleteAll();

            log.info("[COURT] Delete all");
            courtRepository.deleteAll();

            // COUNTRIES
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Afghanistan", "AF", "AFG")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Albania", "AL", "ALB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Algeria", "DZ", "DZA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("American Samoa", "AS", "ASM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Andorra", "AD", "AND")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Angola", "AO", "AGO")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Anguilla", "AI", "AIA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Antarctica", "AQ", "ATA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Antigua and Barbuda", "AG", "ATG")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Argentina", "AR", "ARG")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Armenia", "AM", "ARM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Aruba", "AW", "ABW")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Australia", "AU", "AUS")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Austria", "AT", "AUT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Azerbaijan", "AZ", "AZE")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Bahamas", "BS", "BHS")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Bahrain", "BH", "BHR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Bangladesh", "BD", "BGD")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Barbados", "BB", "BRB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Belarus", "BY", "BLR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Belgium", "BE", "BEL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Belize", "BZ", "BLZ")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Benin", "BJ", "BEN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Bermuda", "BM", "BMU")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Bhutan", "BT", "BTN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Bolivia", "Plurinational State of", "BO")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Bolivia", "BO", "BOL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Bosnia and Herzegovina", "BA", "BIH")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Botswana", "BW", "BWA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Bouvet Island", "BV", "BVT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Brazil", "BR", "BRA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("British Indian Ocean Territory", "IO", "IOT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Brunei Darussalam", "BN", "BRN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Brunei", "BN", "BRN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Bulgaria", "BG", "BGR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Burkina Faso", "BF", "BFA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Burundi", "BI", "BDI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Cambodia", "KH", "KHM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Cameroon", "CM", "CMR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Canada", "CA", "CAN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Cape Verde", "CV", "CPV")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Cayman Islands", "KY", "CYM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Central African Republic", "CF", "CAF")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Chad", "TD", "TCD")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Chile", "CL", "CHL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("China", "CN", "CHN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Christmas Island", "CX", "CXR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Cocos (Keeling) Islands", "CC", "CCK")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Colombia", "CO", "COL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Comoros", "KM", "COM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Congo", "CG", "COG")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Congo", "the Democratic Republic of the", "CD")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Cook Islands", "CK", "COK")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Costa Rica", "CR", "CRI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Côte d'Ivoire", "CI", "CIV")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Ivory Coast", "CI", "CIV")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Croatia", "HR", "HRV")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Cuba", "CU", "CUB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Cyprus", "CY", "CYP")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Czech Republic", "CZ", "CZE")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Denmark", "DK", "DNK")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Djibouti", "DJ", "DJI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Dominica", "DM", "DMA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Dominican Republic", "DO", "DOM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Ecuador", "EC", "ECU")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Egypt", "EG", "EGY")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("El Salvador", "SV", "SLV")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Equatorial Guinea", "GQ", "GNQ")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Eritrea", "ER", "ERI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Estonia", "EE", "EST")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Ethiopia", "ET", "ETH")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Falkland Islands (Malvinas)", "FK", "FLK")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Faroe Islands", "FO", "FRO")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Fiji", "FJ", "FJI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Finland", "FI", "FIN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("France", "FR", "FRA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("French Guiana", "GF", "GUF")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("French Polynesia", "PF", "PYF")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("French Southern Territories", "TF", "ATF")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Gabon", "GA", "GAB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Gambia", "GM", "GMB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Georgia", "GE", "GEO")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Germany", "DE", "DEU")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Ghana", "GH", "GHA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Gibraltar", "GI", "GIB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Greece", "GR", "GRC")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Greenland", "GL", "GRL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Grenada", "GD", "GRD")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Guadeloupe", "GP", "GLP")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Guam", "GU", "GUM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Guatemala", "GT", "GTM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Guernsey", "GG", "GGY")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Guinea", "GN", "GIN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Guinea-Bissau", "GW", "GNB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Guyana", "GY", "GUY")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Haiti", "HT", "HTI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Heard Island and McDonald Islands", "HM", "HMD")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Holy See (Vatican City State)", "VA", "VAT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Honduras", "HN", "HND")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Hong Kong", "HK", "HKG")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Hungary", "HU", "HUN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Iceland", "IS", "ISL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("India", "IN", "IND")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Indonesia", "ID", "IDN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Iran", "Islamic Republic of", "IR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Iraq", "IQ", "IRQ")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Ireland", "IE", "IRL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Isle of Man", "IM", "IMN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Israel", "IL", "ISR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Italy", "IT", "ITA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Jamaica", "JM", "JAM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Japan", "JP", "JPN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Jersey", "JE", "JEY")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Jordan", "JO", "JOR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Kazakhstan", "KZ", "KAZ")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Kenya", "KE", "KEN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Kiribati", "KI", "KIR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Korea", "Democratic People's Republic of", "KP")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Korea", "Republic of", "KR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("South Korea", "KR", "KOR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Kuwait", "KW", "KWT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Kyrgyzstan", "KG", "KGZ")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Lao People's Democratic Republic", "LA", "LAO")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Latvia", "LV", "LVA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Lebanon", "LB", "LBN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Lesotho", "LS", "LSO")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Liberia", "LR", "LBR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Libyan Arab Jamahiriya", "LY", "LBY")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Libya", "LY", "LBY")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Liechtenstein", "LI", "LIE")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Lithuania", "LT", "LTU")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Luxembourg", "LU", "LUX")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Macao", "MO", "MAC")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Macedonia", "the former Yugoslav Republic of", "MK")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Madagascar", "MG", "MDG")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Malawi", "MW", "MWI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Malaysia", "MY", "MYS")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Maldives", "MV", "MDV")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Mali", "ML", "MLI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Malta", "MT", "MLT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Marshall Islands", "MH", "MHL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Martinique", "MQ", "MTQ")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Mauritania", "MR", "MRT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Mauritius", "MU", "MUS")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Mayotte", "YT", "MYT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Mexico", "MX", "MEX")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Micronesia", "Federated States of", "FM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Moldova", "Republic of", "MD")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Monaco", "MC", "MCO")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Mongolia", "MN", "MNG")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Montenegro", "ME", "MNE")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Montserrat", "MS", "MSR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Morocco", "MA", "MAR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Mozambique", "MZ", "MOZ")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Myanmar", "MM", "MMR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Burma", "MM", "MMR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Namibia", "NA", "NAM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Nauru", "NR", "NRU")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Nepal", "NP", "NPL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Netherlands", "NL", "NLD")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Netherlands Antilles", "AN", "ANT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("New Caledonia", "NC", "NCL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("New Zealand", "NZ", "NZL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Nicaragua", "NI", "NIC")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Niger", "NE", "NER")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Nigeria", "NG", "NGA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Niue", "NU", "NIU")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Norfolk Island", "NF", "NFK")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Northern Mariana Islands", "MP", "MNP")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Norway", "NO", "NOR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Oman", "OM", "OMN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Pakistan", "PK", "PAK")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Palau", "PW", "PLW")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Palestinian Territory", "Occupied", "PS")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Panama", "PA", "PAN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Papua New Guinea", "PG", "PNG")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Paraguay", "PY", "PRY")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Peru", "PE", "PER")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Philippines", "PH", "PHL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Pitcairn", "PN", "PCN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Poland", "PL", "POL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Portugal", "PT", "PRT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Puerto Rico", "PR", "PRI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Qatar", "QA", "QAT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Réunion", "RE", "REU")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Romania", "RO", "ROU")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Russian Federation", "RU", "RUS")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Russia", "RU", "RUS")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Rwanda", "RW", "RWA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Saint Helena", "Ascension and Tristan da Cunha", "SH")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Saint Kitts and Nevis", "KN", "KNA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Saint Lucia", "LC", "LCA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Saint Pierre and Miquelon", "PM", "SPM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Saint Vincent and the Grenadines", "VC", "VCT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Saint Vincent & the Grenadines", "VC", "VCT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("St. Vincent and the Grenadines", "VC", "VCT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Samoa", "WS", "WSM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("San Marino", "SM", "SMR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Sao Tome and Principe", "ST", "STP")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Saudi Arabia", "SA", "SAU")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Senegal", "SN", "SEN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Serbia", "RS", "SRB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Seychelles", "SC", "SYC")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Sierra Leone", "SL", "SLE")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Singapore", "SG", "SGP")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Slovakia", "SK", "SVK")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Slovenia", "SI", "SVN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Solomon Islands", "SB", "SLB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Somalia", "SO", "SOM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("South Africa", "ZA", "ZAF")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("South Georgia and the South Sandwich Islands", "GS", "SGS")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("South Sudan", "SS", "SSD")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Spain", "ES", "ESP")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Sri Lanka", "LK", "LKA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Sudan", "SD", "SDN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Suriname", "SR", "SUR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Svalbard and Jan Mayen", "SJ", "SJM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Swaziland", "SZ", "SWZ")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Sweden", "SE", "SWE")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Switzerland", "CH", "CHE")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Syrian Arab Republic", "SY", "SYR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Taiwan", "Province of China", "TW")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Taiwan", "TW", "TWN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Tajikistan", "TJ", "TJK")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Tanzania", "United Republic of", "TZ")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Thailand", "TH", "THA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Timor-Leste", "TL", "TLS")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Togo", "TG", "TGO")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Tokelau", "TK", "TKL")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Tonga", "TO", "TON")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Trinidad and Tobago", "TT", "TTO")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Tunisia", "TN", "TUN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Turkey", "TR", "TUR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Turkmenistan", "TM", "TKM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Turks and Caicos Islands", "TC", "TCA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Tuvalu", "TV", "TUV")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Uganda", "UG", "UGA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Ukraine", "UA", "UKR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("United Arab Emirates", "AE", "ARE")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("United Kingdom", "GB", "GBR")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("United States", "US", "USA")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("United States Minor Outlying Islands", "UM", "UMI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Uruguay", "UY", "URY")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Uzbekistan", "UZ", "UZB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Vanuatu", "VU", "VUT")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Venezuela", "VE", "VEN")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Viet Nam", "VN", "VNM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Vietnam", "VN", "VNM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Virgin Islands", "British", "VG")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Virgin Islands", "U.S.", "VI")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Wallis and Futuna", "WF", "WLF")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Western Sahara", "EH", "ESH")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Yemen", "YE", "YEM")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Zambia", "ZM", "ZMB")));
            log.info("[COUNTRY] Preloading " + countryRepository.save(new Country("Zimbabwe", "ZW", "ZWE")));

            // PERSONS

            // Womans
            Person playerF1 = new Person("Iga", "Swiatek");
            playerF1.setGender(EGender.FEMALE);
            playerF1.setBirthDate(LocalDate.parse("2001-05-31"));
            playerF1.setHeight(175);
            playerF1.setBirthPlace("Varsosie, Pologne");
            playerF1.setIsCoach(false);
            playerF1.setIsPlayer(true);
            playerF1.setHand(EHand.RIGHT);
            playerF1.setPicture("https://images.prismic.io/fft-rg-site/1a1fe64f-69f1-429d-a6d2-1be8f110be47_20200308_PJ_SwiatekIga_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF1.setRanking(1);
            playerF1.setNationality(countryRepository.findByName("Poland"));

            Person playerF2 = new Person("Ons", "Jabeur");
            playerF2.setGender(EGender.FEMALE);
            playerF2.setBirthDate(LocalDate.parse("1994-08-28"));
            playerF2.setBirthPlace("Ksar Hellal, Tunisie");
            playerF2.setHeight(167);
            playerF2.setHand(EHand.RIGHT);
            playerF2.setIsCoach(false);
            playerF2.setIsPlayer(true);
            playerF2.setPicture("https://images.prismic.io/fft-rg-site/0942c177-cde4-4f29-8c11-5ec5a19df99e_20220427_PJ_JabeurOns_US.PNG?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF2.setRanking(2);
            playerF2.setNationality(countryRepository.findByName("Tunisia"));

            Person playerF3 = new Person("Jessica", "Pegula");
            playerF3.setGender(EGender.FEMALE);
            playerF3.setBirthPlace("Buffalo, New York, Etats-Unis");
            playerF3.setBirthDate(LocalDate.parse("1994-02-24"));
            playerF3.setHeight(170);
            playerF3.setHand(EHand.RIGHT);
            playerF3.setIsCoach(false);
            playerF3.setIsPlayer(true);
            playerF3.setPicture("https://images.prismic.io/fft-rg-site/39fed296-454c-445b-8988-0390211d9626_20220427_PJ_PegulaJessica_US.PNG?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF3.setRanking(3);
            playerF3.setNationality(countryRepository.findByName("United States"));

            Person playerF4 = new Person("Caroline", "Garcia");
            playerF4.setGender(EGender.FEMALE);
            playerF4.setBirthDate(LocalDate.parse("1993-10-16"));
            playerF4.setBirthPlace("Saint-Germain-en-Laye, France");
            playerF4.setHeight(177);
            playerF4.setHand(EHand.RIGHT);
            playerF4.setIsCoach(false);
            playerF4.setIsPlayer(true);
            playerF4.setPicture("https://images.prismic.io/fft-rg-site/3dbbc6e2-ed47-4a23-af86-495a4c9e2ca8_20180524_PJ_GarciaCaroline_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF4.setRanking(4);
            playerF4.setNationality(countryRepository.findByName("France"));

            Person playerF5 = new Person("Aryna", "Sabalenka");
            playerF5.setGender(EGender.FEMALE);
            playerF5.setBirthPlace("Minsk, Biélorussie");
            playerF5.setBirthDate(LocalDate.parse("1998-05-05"));
            playerF5.setHeight(182);
            playerF5.setHand(EHand.RIGHT);
            playerF5.setIsCoach(false);
            playerF5.setIsPlayer(false);
            playerF5.setPicture("https://images.prismic.io/fft-rg-site/f38acf23-789e-4d62-b39d-0cf22797e1a3_20190302_PJ_SabalenkaAryna_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF5.setRanking(5);
            playerF5.setNationality(countryRepository.findByName("Belarus"));

            Person playerF6 = new Person("Maria", "Sakkari");
            playerF6.setGender(EGender.FEMALE);
            playerF6.setBirthPlace("Athènes, Grèce");
            playerF6.setBirthDate(LocalDate.parse("1995-08-25"));
            playerF6.setHeight(172);
            playerF6.setHand(EHand.RIGHT);
            playerF6.setIsCoach(false);
            playerF6.setIsPlayer(true);
            playerF6.setPicture("https://images.prismic.io/fft-rg-site/f2be46ed-3c69-4f45-bd34-f4c2ea568929_20200311_PJ_SakkariMaria_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF6.setRanking(6);
            playerF6.setNationality(countryRepository.findByName("Greece"));

            Person playerF7 = new Person("Coco", "Gauff");
            playerF7.setGender(EGender.FEMALE);
            playerF7.setBirthDate(LocalDate.parse("2004-03-13"));
            playerF7.setBirthPlace("Delray Beach, Fl, États-Unis");
            playerF7.setHeight(175);
            playerF7.setHand(EHand.RIGHT);
            playerF7.setIsCoach(false);
            playerF7.setIsPlayer(true);
            playerF7.setPicture("https://images.prismic.io/fft-rg-site/2faad022-abf7-48e4-842a-34534366b5fd_20200310_PJ_GauffCory_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF7.setRanking(7);
            playerF7.setNationality(countryRepository.findByName("United States"));

            Person playerF8 = new Person("Daria", "Kasatkina");
            playerF8.setGender(EGender.FEMALE);
            playerF8.setBirthPlace("Togliatti, Russie");
            playerF8.setBirthDate(LocalDate.parse("1997-05-07"));
            playerF8.setHeight(170);
            playerF8.setIsCoach(false);
            playerF8.setIsPlayer(true);
            playerF8.setPicture("https://images.prismic.io/fft-rg-site/45777401-3e3b-459c-98d9-dc54f00547e9_20190306_PJ_KasatkinaDaria_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF8.setRanking(8);
            playerF8.setNationality(countryRepository.findByName("Russia"));

            // Mans
            Person playerM1 = new Person("Carlos", "Alcaraz");
            playerM1.setGender(EGender.MALE);
            playerM1.setHand(EHand.RIGHT);
            playerM1.setBirthPlace("El Palmar, Murcia, Espagne");
            playerM1.setBirthDate(LocalDate.parse("2003-05-05"));
            playerM1.setHeight(182);
            playerM1.setWeight(74);
            playerM1.setIsCoach(false);
            playerM1.setIsPlayer(true);
            playerM1.setEarlyCareer(LocalDate.parse("2018-01-01"));
            playerM1.setPicture("https://images.prismic.io/fft-rg-site/6ca816f6-ef3f-4633-9a97-155651ca4857_20220308_PJ_AlcarazCarlos_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerM1.setRanking(1);
            playerM1.setNationality(countryRepository.findByName("Spain"));

            Person playerM2 = new Person("Rafael", "Nadal");
            playerM2.setGender(EGender.MALE);
            playerM2.setBirthPlace("Manacor, Espagne");
            playerM2.setBirthDate(LocalDate.parse("1986-06-03"));
            playerM2.setEarlyCareer(LocalDate.parse("2001-01-01"));
            playerM2.setHand(EHand.LEFT);
            playerM2.setHeight(185);
            playerM2.setWeight(84);
            playerM2.setIsCoach(false);
            playerM2.setIsPlayer(true);
            playerM2.setPicture("https://images.prismic.io/fft-rg-site/29bcebad-30f5-46c2-a7dd-e648518dea74_20220310_PJ_NadalRafael_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerM2.setRanking(2);
            playerM2.setNationality(countryRepository.findByName("Spain"));

            Person playerM3 = new Person("Stefanos", "Tsitsipas");
            playerM3.setGender(EGender.MALE);
            playerM3.setBirthPlace("Athènes, Grèce");
            playerM3.setBirthDate(LocalDate.parse("1998-08-12"));
            playerM3.setHand(EHand.RIGHT);
            playerM3.setEarlyCareer(LocalDate.parse("2016-01-01"));
            playerM3.setHeight(193);
            playerM3.setWeight(89);
            playerM3.setIsCoach(false);
            playerM3.setIsPlayer(true);
            playerM3.setPicture("https://images.prismic.io/fft-rg-site/6bbfe32a-28dd-4c05-9499-fd96dd1b10d0_20200119_PJ_TsitsipasStephanos_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerM3.setRanking(3);
            playerM3.setNationality(countryRepository.findByName("Greece"));

            log.info("[PERSON] Preloading " + personRepository.save(playerF1));
            log.info("[PERSON] Preloading " + personRepository.save(playerF2));
            log.info("[PERSON] Preloading " + personRepository.save(playerF3));
            log.info("[PERSON] Preloading " + personRepository.save(playerF4));
            log.info("[PERSON] Preloading " + personRepository.save(playerF5));
            log.info("[PERSON] Preloading " + personRepository.save(playerF6));
            log.info("[PERSON] Preloading " + personRepository.save(playerF7));
            log.info("[PERSON] Preloading " + personRepository.save(playerF8));
            log.info("[PERSON] Preloading " + personRepository.save(playerM1));
            log.info("[PERSON] Preloading " + personRepository.save(playerM2));

            // ROLES
            Role roleUser = new Role(ERole.ROLE_USER);
            Role roleEditorPlayer = new Role(ERole.ROLE_EDITOR_PLAYER);
            Role roleEditorMatch = new Role(ERole.ROLE_EDITOR_MATCH);
            Role roleAdmin = new Role(ERole.ROLE_ADMIN);

            log.info("[ROLE] Preloading " + roleRepository.save(roleUser));
            log.info("[ROLE] Preloading " + roleRepository.save(roleEditorPlayer));
            log.info("[ROLE] Preloading " + roleRepository.save(roleEditorMatch));
            log.info("[ROLE] Preloading " + roleRepository.save(roleAdmin));

            // USERS
            User admin = new User("admin", "admin@email.com", "$2a$10$iSa6.Wh9ZAghYsiL/xfR6u4mnw0UJ.Tc6nDSwo8OxxKZU8r/PQ27m");
            HashSet<Role> adminRoles1 = new HashSet<>();
            adminRoles1.add(roleRepository.findByName(ERole.ROLE_ADMIN));
            admin.setRoles(adminRoles1);

            User adminMatch = new User("admin-match", "admin1@email.com", "$2a$10$iSa6.Wh9ZAghYsiL/xfR6u4mnw0UJ.Tc6nDSwo8OxxKZU8r/PQ27m");
            HashSet<Role> adminRoles2 = new HashSet<>();
            adminRoles2.add(roleRepository.findByName(ERole.ROLE_EDITOR_MATCH));
            adminMatch.setRoles(adminRoles2);

            User adminPlayer = new User("admin-player", "admin2@email.com", "$2a$10$iSa6.Wh9ZAghYsiL/xfR6u4mnw0UJ.Tc6nDSwo8OxxKZU8r/PQ27m");
            HashSet<Role> adminRoles3 = new HashSet<>();
            adminRoles3.add(roleRepository.findByName(ERole.ROLE_EDITOR_PLAYER));
            adminPlayer.setRoles(adminRoles3);

            User adminNoRule = new User("admin-norole", "admin3@email.com", "$2a$10$iSa6.Wh9ZAghYsiL/xfR6u4mnw0UJ.Tc6nDSwo8OxxKZU8r/PQ27m");

            log.info("[USER] Preloading " + userRepository.save(admin));
            log.info("[USER] Preloading " + userRepository.save(adminPlayer));
            log.info("[USER] Preloading " + userRepository.save(adminMatch));
            log.info("[USER] Preloading " + userRepository.save(adminNoRule));

            // COURTS

            Court court1 = new Court("Court Leane");
            Court court2 = new Court("Court Mathis");
            Court court3 = new Court("Court Sylvain");
            Court court4 = new Court("Court Paul");

            log.info("[COURT] Preloading " + courtRepository.save(court1));
            log.info("[COURT] Preloading " + courtRepository.save(court2));
            log.info("[COURT] Preloading " + courtRepository.save(court3));
            log.info("[COURT] Preloading " + courtRepository.save(court4));

            // TRAININGS

            Training training1 = new Training();
            training1.setDetails("Entrainement du samedi matin");
            training1.setDuration(2);
            training1.setStartDate(LocalDateTime.parse("2022-11-26T10:00:00"));
            training1.setPlayer(personRepository.findByFirstnameAndLastname("Ons", "Jabeur").get());
            training1.setCourt(courtRepository.findByName("Court Leane").get());

            log.info("[TRAINING] Preloading " + trainingRepository.save(training1));

            Training training2 = new Training();
            training2.setDetails("Entrainement du samedi midi");
            training2.setDuration(2);
            training2.setStartDate(LocalDateTime.parse("2022-11-26T12:00:00"));
            training2.setPlayer(personRepository.findByFirstnameAndLastname("Coco", "Gauff").get());
            training2.setCourt(courtRepository.findByName("Court Leane").get());

            log.info("[TRAINING] Preloading " + trainingRepository.save(training2));

            Training training3 = new Training();
            training3.setDetails("Entrainement du lundi soir");
            training3.setDuration(2);
            training3.setStartDate(LocalDateTime.parse("2022-11-28T12:00:00"));
            training3.setPlayer(personRepository.findByFirstnameAndLastname("Coco", "Gauff").get());
            training3.setCourt(courtRepository.findByName("Court Leane").get());

            log.info("[TRAINING] Preloading " + trainingRepository.save(training3));
        };
    }

}
