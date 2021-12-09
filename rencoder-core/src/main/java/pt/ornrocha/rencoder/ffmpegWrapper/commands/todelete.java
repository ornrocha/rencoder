package pt.ornrocha.rencoder.ffmpegWrapper.commands;

import java.util.List;
import java.util.Locale;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageAlpha3Code;
import com.neovisionaries.i18n.LanguageCode;

public class todelete {

  public static void main(String[] args) {
    // String[] languages = Locale.getISOLanguages();
    // Locale locale = new Locale("ENGLISH", "US", "WIN");
    //
    // for (String language : languages) {
    // //Locale locale = new Locale(language);
    // String isocode = locale.getISO3Language();
    // String lang = locale.getDisplayLanguage();
    // System.out.println(isocode+" -> "+lang);
    //
    // }

    // String[] languages = Locale.getISOLanguages();
    // for (String language : languages) {
    // Locale locale = new Locale(language);
    // String lang = locale.getDisplayLanguage();
    // List<LanguageAlpha3Code> list = LanguageAlpha3Code.findByName(lang);
    // System.out.println(lang+" -> "+list);
    // }
    // for (Locale locale : Locale.getAvailableLocales()) {
    // System.out.println("" + locale
    // + "; display: " + locale.getDisplayLanguage()
    // + "; name: " + locale.getDisplayName()
    // + "; lang: " + locale.getLanguage()
    // + "; iso3: " + locale.getISO3Language());
    // }

    // for (CountryCode code : CountryCode.values())
    // {
    // System.out.println("Country name = " + code.getName());
    // System.out.println("ISO 3166-1 alpha-2 code = " + code.getAlpha2());
    // System.out.println("ISO 3166-1 alpha-3 code = " + code.getAlpha3());
    // System.out.println("ISO 3166-1 numeric code = " + code.getNumeric());
    // }

    // for (LanguageCode code : LanguageCode.values())
    // {
    // System.out.format("[%s] %s\n", code, code.getName());
    // }

    for (LanguageAlpha3Code code : LanguageAlpha3Code.values()) {
      System.out.println(code.toString() + "-->" + code.getName());
    }

  }

}
