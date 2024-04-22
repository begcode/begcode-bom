package tech.jhipster.config.cache;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;

class PrefixedKeyGeneratorTest {

    @Test
    void generatePrefixFromShortCommitId() {
        Properties gitProperties = new Properties();
        gitProperties.put("commit.id.abbrev", "1234");

        PrefixedKeyGenerator prefixedKeyGenerator = new PrefixedKeyGenerator(new GitProperties(gitProperties), null);

        assertThat(prefixedKeyGenerator.getPrefix()).isEqualTo("1234");
    }

    @Test
    void generatePrefixFromCommitId() {
        Properties gitProperties = new Properties();
        gitProperties.put("commit.id", "1234567");

        PrefixedKeyGenerator prefixedKeyGenerator = new PrefixedKeyGenerator(new GitProperties(gitProperties), null);

        assertThat(prefixedKeyGenerator.getPrefix()).isEqualTo("1234567");
    }

    @Test
    void generatePrefixFromBuildVersion() {
        Properties buildProperties = new Properties();
        buildProperties.put("version", "1.0.0");

        PrefixedKeyGenerator prefixedKeyGenerator = new PrefixedKeyGenerator(null, new BuildProperties(buildProperties));

        assertThat(prefixedKeyGenerator.getPrefix()).isEqualTo("1.0.0");
    }

    @Test
    void generatePrefixFromBuildTime() {
        Properties buildProperties = new Properties();
        buildProperties.put("time", "1583955265");

        PrefixedKeyGenerator prefixedKeyGenerator = new PrefixedKeyGenerator(null, new BuildProperties(buildProperties));

        assertThat(prefixedKeyGenerator.getPrefix()).isEqualTo("1970-01-19T07:59:15.265Z");
    }

    @Test
    void generatesRandomPrefix() {
        PrefixedKeyGenerator prefixedKeyGenerator = new PrefixedKeyGenerator(null, null);

        assertThat(prefixedKeyGenerator.getPrefix().length()).isEqualTo(12);
    }
}
