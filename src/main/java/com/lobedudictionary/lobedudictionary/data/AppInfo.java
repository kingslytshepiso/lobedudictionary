package com.lobedudictionary.lobedudictionary.data;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AppInfo {
    private final String name = "Lobedu dictionary Application(An experimental project)";
    private final String version = "V1";
    private final String description = """
            A dictionary application for 'Khelobedu', 'Selobedu' or 'Selozwi' language;
            a dialect for Nothern Sotho and Sepedi. Geographically, the Balobedu are located among three
            ethnic groups: The Vhavenḓa (Tshivenḓa speakers),
            the Bapedi (Sepedi speakers) and the Vhatsonga
            (Xitsonga speakers) who are commonly found in most
            of the Bolobedu villages
            """;
    private final List<String> links = List
            .of("https://www.tandfonline.com/doi/pdf/10.1080/02572117.2022.2094049",
                    "https://www.101lasttribes.com/tribes/balobedu.html");

    public List<String> getLinks() {
        return links;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

}
