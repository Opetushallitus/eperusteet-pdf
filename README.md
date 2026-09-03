# ePerusteet-pdf

[![Build Status](https://github.com/Opetushallitus/eperusteet-pdf/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-pdf/actions)

## 1. Palvelun tehtävä

PDF-dokumenttien generointipalvelu ePerusteet-palvelukokonaisuudelle. Palvelu tuottaa PDF-muotoisia dokumentteja opetussuunnitelmien perusteista, paikallisista opetussuunnitelmista ja ammatillisen koulutuksen järjestämissuunnitelmista.

## 2. Arkkitehtuuri

Javalla ja Spring Boot -viitekehyksellä toteutettu REST API -palvelu.

**Teknologiat:**
- Spring Boot 4.x
- Apache FOP (XSL-FO to PDF)
- Apache PDFBox
- Maven build

**Integraatiot:**
- Tarjoaa REST-rajapinnan PDF-dokumenttien generointiin
- Hakee dokumenttien sisällön muista ePerusteet-palveluista:
  - eperusteet-service
  - eperusteet-amosaa-service
  - eperusteet-ylops-service
- CAS-autentikaatio
- Koodistopalvelu

Palvelu ei käytä tietokantaa vaan toimii tilattomana palveluna, joka generoi PDF-dokumentteja muista palveluista haettavasta datasta.

## 3. Kehitysympäristö

### 3.1. Esivaatimukset

Asenna haluamallasi tavalla:

- Amazon Corretto JDK 21 tai uudempi
- Maven 3.8 tai uudempi
- konfiguroi Maven `~/.m2/settings.xml` GitHub Packages -kirjautumista varten (ks. alla)

**Maven ja GitHub Packages:**

Riippuvuudet (mm. `eperusteet-parent-pom`) haetaan GitHub Packagesista. Ilman autentikaatiota ensimmäinen `mvn`-ajo epäonnistuu.

Lisää `~/.m2/settings.xml`-tiedostoon server-id `github`, joka vastaa pom.xml:n repository-id:tä. Mallina voi käyttää eperusteet-repon [`.github/maven/settings.xml`](https://github.com/Opetushallitus/eperusteet/blob/master/.github/maven/settings.xml)-tiedostoa. Lokaalisti käytä GitHub-käyttäjätunnusta ja [personal access tokenia](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens) (`read:packages` -oikeus).

**Huomioitavaa riippuvuuksista:**

Ajoaikana palvelu riippuu seuraavista OPH-palveluista:
- **CAS** - keskitetty autentikaatio
- **eperusteet-service** - perusteiden sisältö (`localhost:8080` local-profiilissa)
- **eperusteet-ylops-service** - paikallisten opetussuunnitelmien sisältö (`localhost:8081`)
- **eperusteet-amosaa-service** - AMOSAA-järjestämissuunnitelmien sisältö (`localhost:8082`)
- **Koodistopalvelu** - koodistojen hallinta

### 3.2. Testien ajaminen

Aja testit komennolla:

```bash
mvn clean install
```

Vain yksikkötestit ilman integraatiotestejä:

```bash
mvn test
```

### 3.3. Ajaminen lokaalisti

#### 3.3.1. Palvelun käynnistys

Palvelun saa käyntiin seuraavilla komennoilla:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=default,local
```

Tai käytä mukana tulevaa start-skriptiä:

```bash
./start.sh
```

Oletusprofiili on `local`. Voit antaa profiilit myös argumenttina, esim. `./start.sh local`.

Palvelu käynnistyy oletuksena porttiin 8083. API on käytettävissä osoitteessa `http://localhost:8083/eperusteet-pdf-service/api`

**Huom:** Lokaalissa kehityksessä palvelu tarvitsee toimiakseen pääsyn muihin ePerusteet-palveluihin tai niiden mock-toteutuksiin.

### 3.4. IDE setup

IDEAssa saattaa olla helpompi avata projekti suoraan juuresta.

Suositeltavat asetukset:
- Aseta Maven automaattinen import päälle
- Käytä projektiin asetettua Java-versiota (21)

### 3.5. Versiohallinta

Git käytäntönä projektissa on suosittu kehityshaaran squashausta päähaaraan mergettäessä.

### 3.6. Yleisiä ongelmatilanteita

**Maven build epäonnistuu:**
- Varmista että `~/.m2/settings.xml` sisältää GitHub Packages -tunnukset (server-id `github`)
- Tarkista internet-yhteys ja että personal access tokenilla on `read:packages` -oikeus
- Tyhjennä Maven cache: `mvn dependency:purge-local-repository`

**Palvelu ei käynnisty:**
- Tarkista että portti 8083 on vapaana
- Tarkista että määritetyt ePerusteet-palvelut ovat saavutettavissa
- Tarkista konsoliloki

**PDF-generointi epäonnistuu:**
- Varmista että FOP-konfiguraatio löytyy määritetystä polusta (`classpath:docgen/fop-dev.xconf` local-profiilissa)
- Tarkista että tarvittavat fontit ovat saatavilla
- Tarkista että XSL-tiedostot ovat oikeassa muodossa

## 4. Ympäristöt

### 4.1. Testiympäristö

Testiympäristö löytyy osoitteesta [virkailija.testiopintopolku.fi/eperusteet-pdf-service](https://virkailija.testiopintopolku.fi/eperusteet-pdf-service/)

### 4.2. Tuotantoympäristö

Tuotantoympäristö löytyy osoitteesta [virkailija.opintopolku.fi/eperusteet-pdf-service](https://virkailija.opintopolku.fi/eperusteet-pdf-service/)

### 4.3. Lokit

Lokit löytyvät AWS:n CloudWatch-palvelusta.

### 4.4. Continuous Integration

Buildipalveluna käytetään GitHub Actionsia.

Pushaaminen remoteen käynnistää:
1. Testien ajamisen
2. Sovelluksen buildauksen
3. Kontti-imagen luonnin OPH:n deploytyökaluja varten
4. Imagen pushaus AWS ECR:ään

## 5. ePerusteet-projektit

|Projekti | Build status |
|-----|-----|
|[ePerusteet](https://github.com/Opetushallitus/eperusteet)|[![Build Status](https://github.com/Opetushallitus/eperusteet/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet/actions)|
|[ePerusteet-amosaa](https://github.com/Opetushallitus/eperusteet-amosaa) | [![Build Status](https://github.com/Opetushallitus/eperusteet-amosaa/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-amosaa/actions)|
|[ePerusteet-ylops](https://github.com/Opetushallitus/eperusteet-ylops) | [![Build Status](https://github.com/Opetushallitus/eperusteet-ylops/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-ylops/actions)|
|[ePerusteet-ui](https://github.com/Opetushallitus/eperusteet-ui) | [![Build Status](https://github.com/Opetushallitus/eperusteet-ui/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-ui/actions)|
|[eperusteet-ylops-ui](https://github.com/Opetushallitus/eperusteet-ylops-ui) | [![Build Status](https://github.com/Opetushallitus/eperusteet-ylops-ui/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-ylops-ui/actions) |
|[ePerusteet-amosaa-ui](https://github.com/Opetushallitus/eperusteet-amosaa-ui) | [![Build Status](https://github.com/Opetushallitus/eperusteet-amosaa-ui/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-amosaa-ui/actions)|
|[ePerusteet-opintopolku](https://github.com/Opetushallitus/eperusteet-opintopolku) | [![Build Status](https://github.com/Opetushallitus/eperusteet-opintopolku/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-opintopolku/actions) |
|[ePerusteet-backend-utils](https://github.com/Opetushallitus/eperusteet-backend-utils) | [![Build Status](https://github.com/Opetushallitus/eperusteet-backend-utils/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-backend-utils/actions)|
|[ePerusteet-frontend-utils](https://github.com/Opetushallitus/eperusteet-frontend-utils) | [![Build Status](https://github.com/Opetushallitus/eperusteet-frontend-utils/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-frontend-utils/actions) |
|[ePerusteet-pdf](https://github.com/Opetushallitus/eperusteet-pdf) | [![Build Status](https://github.com/Opetushallitus/eperusteet-pdf/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-pdf/actions) |
|[eperusteet-e2e-smoke-test](https://github.com/Opetushallitus/eperusteet-e2e-smoke-test) | [![Build Status](https://github.com/Opetushallitus/eperusteet-e2e-smoke-test/actions/workflows/playwright.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-e2e-smoke-test/actions)|

## 6. Lisätiedot

### 6.1. Dokumentaatio

- [Palvelukortti](https://wiki.eduuni.fi/spaces/ophPPK/pages/450081297/ePerusteet) - Yleiskatsaus palveluun

### 6.2. Lisenssi

EUPL 1.1

### 6.3. Yhteystiedot

Opetushallitus / ePerusteet-tiimi
