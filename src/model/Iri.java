package model;

import java.util.Arrays;
import java.util.List;

public class Iri {
    private final String urlBase;
    private final String firstName;
    private final String secondName;

    public Iri(String url, boolean isReversed) {
        List<String> baseAndName = splitIntoBaseAndName(url);
        urlBase = baseAndName.get(0) + "#";
        List<String> names = splitName(baseAndName.get(1));
        if (isReversed && names.size() == 1) {
            throw new IllegalArgumentException("Url is reversed but cannot create reversed name " + url);
        }
        firstName = names.get(isReversed ? 1 : 0);
        if (names.size() > 1) {
            secondName = names.get(isReversed ? 0 : 1);
        } else {
            secondName = "";
        }
    }

    private List<String> splitIntoBaseAndName(String iri) {
        List<String> strings = Arrays.asList(iri.split("#", -1));
        if (strings.size() != 2) {
            throw new IllegalArgumentException("Invalid IRI: " + iri);
        }
        return strings;
    }

    private List<String> splitName(String name) {
        List<String> strings = Arrays.asList(name.split("\\.", -1));
        if (strings.size() > 2) {
            throw new IllegalArgumentException("Invalid name: " + name);
        }
        return strings;
    }

    public String getIri() {
        return urlBase + firstName + (!secondName.isEmpty() ? "." + secondName : "");
    }

    public String getReversedIri() {
        if (secondName.isEmpty()) {
            return urlBase + firstName;
        } else {
            return urlBase + secondName + (!firstName.isEmpty() ? "." + firstName : "");
        }
    }

    public String getSystemId() {
        if (firstName.isEmpty()) {
            return "";
        }
        StringBuilder systemId = new StringBuilder();
        List<String> firstNameFirstRest = splitIntoFirstAndRest(firstName);
        systemId.append(firstNameFirstRest.get(0)).append(firstNameFirstRest.get(1));
        if (!secondName.isEmpty()) {
            List<String> secondNameFirstRest = splitIntoFirstAndRest(secondName);
            systemId.append("_").append(secondNameFirstRest.get(0)).append(secondNameFirstRest.get(1));
        }
        return systemId.toString();
    }

    private List<String> splitIntoFirstAndRest(String name) {
        String firstChar = name.substring(0, 1).toLowerCase();
        String firstRest = name.substring(1);
        return Arrays.asList(firstChar, firstRest);
    }
}
