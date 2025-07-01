package utils;

import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

@Slf4j
public class TestDataUtils {

    public static String resolveYaml(String input) {
        if (input == null || !input.startsWith(":KW_")) {
            return input;
        }

        try {
            String raw = input.substring(4); // Bỏ :KW_
            String[] parts = raw.split("\\[|\\]");
            if (parts.length != 2) throw new IllegalArgumentException("Invalid format");

            String filePath = parts[0]; // VD: "keywords/home" hoặc "mobile/homepage"
            String[] keys = parts[1].split("\\.");

            InputStream inputStream = TestDataUtils.class.getClassLoader()
                    .getResourceAsStream("config/" + filePath + ".yml");
            if (inputStream == null)
                throw new RuntimeException("YAML file not found: config/" + filePath + ".yml");

            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);

            Object current = data;
            for (String key : keys) {
                if (!(current instanceof Map)) {
                    throw new RuntimeException("Invalid structure at key: " + key);
                }
                current = ((Map<?, ?>) current).get(key);
                if (current == null) {
                    throw new RuntimeException("Key not found: " + key);
                }
            }

            return current.toString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to resolve :KW_ keyword: " + input, e);
        }
    }


    public static String getStoreData(String input) {
        if (input == null) return null;

        if (input.startsWith(":KW_")) {
            return resolveYaml(input);
        } else if (input.startsWith(":SPEC_")) {
            return resolveGaugeStore(input, ":SPEC_", Scope.SPEC);
        } else if (input.startsWith(":SCENARIO_")) {
            return resolveGaugeStore(input, ":SCENARIO_", Scope.SCENARIO);
        } else if (input.startsWith(":SUITE_")) {
            return resolveGaugeStore(input, ":SUITE_", Scope.SUITE);
        }

        return input;
    }

    private enum Scope {
        SCENARIO, SPEC, SUITE
    }

    private static String resolveGaugeStore(String input, String prefix, Scope scope) {
        String key = input.substring(prefix.length());
        Object value;

        switch (scope) {
            case SCENARIO:
                value = ScenarioDataStore.get(key);
                break;
            case SPEC:
                value = SpecDataStore.get(key);
                break;
            case SUITE:
                value = SuiteDataStore.get(key);
                break;
            default:
                throw new IllegalArgumentException("Unsupported scope: " + scope);
        }

        if (value == null) {
            throw new RuntimeException("Key not found in store (" + prefix + "): " + key);
        }

        return value.toString();
    }

    public static void setStoreData(String prefixKey, Object value) {
        if (prefixKey == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null.");
        }

        if (prefixKey.startsWith(":SP_")) {
            // SP = Spec
            String key = prefixKey.substring(4);
            SpecDataStore.put(key, value);
        } else if (prefixKey.startsWith(":SC_")) {
            // SC = Scenario
            String key = prefixKey.substring(4);
            ScenarioDataStore.put(key, value);
        } else if (prefixKey.startsWith(":ST_")) {
            // ST = Suite
            String key = prefixKey.substring(4);
            SuiteDataStore.put(key, value);
        } else {
            throw new IllegalArgumentException("Unsupported prefix for setStoreData: " + prefixKey);
        }
    }
}
