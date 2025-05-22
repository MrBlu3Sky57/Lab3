package org.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
* Main class for this program.
* Complete the code according to the "to do" notes.<br/>
* The system will:<br/>
* - prompt the user to pick a country name from a list<br/>
* - prompt the user to pick the language they want it translated to
*   from a list<br/>
* - output the translation<br/>
* - at any time, the user can type quit to quit the program<br/>
*/
public class Main {
    /**
    * This is the main entry point of our Translation System!<br/>
    * A class implementing the Translator interface is created and
    *   passed into a call to runProgram.
    * @param args not used by the program
    */
    public static void main(final String[] args) {
        // Translator translator = new JSONTranslator(null);
        Translator translator = new InLabByHandTranslator();
        runProgram(translator);
    }
    /**
    * This is the method which we will use to test your overall program, since
    * it allows us to pass in whatever translator object that we want!
    * See the class Javadoc for a summary of what the program will do.
    * @param translator the Translator implementation to use in the program
    */
    public static void runProgram(final Translator translator) {
        while (true) {
            String country = promptForCountry(translator);
            if (country.equals("quit")) {
                break;
            }
            CountryCodeConverter converter = new CountryCodeConverter();
            String language =
                promptForLanguage(translator, converter.fromCountry(country));
            if (language.equals("quit")) {
                break;
            }
            LanguageCodeConverter languageConverter
                = new LanguageCodeConverter();
            System.out.println(country + " in " + language + " is "
                                + translator.translate(country,
                                languageConverter.fromLanguage(language)));
            System.out.println("Press enter to continue or quit to exit.");
            Scanner s = new Scanner(System.in);
            String textTyped = s.nextLine();
            if ("quit".equals(textTyped)) {
                break;
            }
            s.close();
        }
    }
    // Note: CheckStyle is configured so that we don't
    // need javadoc for private methods
    private static String promptForCountry(final Translator translator) {
        List<String> countries = translator.getCountries();
        List<String> countryNames = new ArrayList<String>();
        CountryCodeConverter converter = new CountryCodeConverter();
        for (int i = 0; i < countries.size(); i++) {
            countryNames.add(converter.fromCountryCode(countries.get(i)));
        }
        Collections.sort(countries);
        countries.forEach((String country) -> {
            System.out.println(country);
        });
        System.out.println("select a country from above:");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
    // Note: CheckStyle is configured so that we don't need javadoc for private
    // methods
    private static String
        promptForLanguage(final Translator translator, final String country) {
        // TODO Task: replace the line below so that we sort the languages
        // alphabetically and print them out; one per line TODO Task: convert
        // the language codes to the actual language names before sorting
        List<String> languages = translator.getCountryLanguages(country);
        Collections.sort(languages);
        languages.forEach((String language) -> {
            System.out.println(language);
        });
        System.out.println("select a language from above:");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
