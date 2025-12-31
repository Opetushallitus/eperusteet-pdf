# ePerusteet-pdf

[![Build Status](https://github.com/Opetushallitus/eperusteet-pdf/actions/workflows/build.yml/badge.svg)](https://github.com/Opetushallitus/eperusteet-pdf/actions)

## 1. Palvelun tehtävä

PDF-dokumenttien generointipalvelu ePerusteet-palvelukokonaisuudelle. Palvelu tuottaa PDF-muotoisia dokumentteja opetussuunnitelmien perusteista, paikallisista opetussuunnitelmista ja ammatillisen koulutuksen järjestämissuunnitelmista.

## 2. Arkkitehtuuri

Javalla ja Spring Boot -viitekehyksellä toteutettu REST API -palvelu.

**Teknologiat:**
- Spring Boot 3.x
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

Asenna haluammallasi tavalla:

- Amazon Corretto JDK 17 tai uudempi
- Maven 3.8 tai uudempi

**Huomioitavaa riippuvuuksista:**

Käännösaikana tarvitaan pääsy OPH:n sisäiseen pakettien hallintaan, koska osa paketeista (esim. eperusteet-parent-pom) ei ole julkisissa Maven-repoissa. Konfiguroi Maven settings.xml tiedosto dev-settings.md ohjeiden mukaisesti.

Ajoaikana palvelu riippuu seuraavista OPH-palveluista:
- **CAS** - keskitetty autentikaatio
- **eperusteet-service** - perusteiden sisältö
- **eperusteet-amosaa-service** - AMOSAA-järjestämissuunnitelmien sisältö
- **eperusteet-ylops-service** - paikallisten opetussuunnitelmien sisältö
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
mvn spring-boot:run -Dspring-boot.run.profiles=default,dev
```

Tai käytä mukana tulevaa start-skriptiä:

```bash
./start.sh dev
```

Palvelu käynnistyy oletuksena porttiin 8080. API on käytettävissä osoitteessa `http://localhost:8080/eperusteet-pdf-service/api`

**Huom:** Lokaalissa kehityksessä palvelu tarvitsee toimiakseen pääsyn muihin ePerusteet-palveluihin tai niiden mock-toteutuksiin.

### 3.4. IDE setup

IDEAssa saattaa olla helpompi avata projekti suoraan juuresta.

Suositeltavat asetukset:
- Aseta Maven automaattinen import päälle
- Käytä projektiin asetettua Java-versiota
- Aseta koodiformatointi käyttämään projektin määrittelemiä sääntöjä

### 3.5. Versiohallinta

Git käytäntönä projektissa on suosittu kehityshaaran squashausta päähaaraan mergettäessä.

### 3.6. Yleisiä ongelmatilanteita

**Maven build epäonnistuu:**
- Varmista että Maven settings.xml on konfiguroitu oikein (dev-settings.md)
- Tarkista internet-yhteys OPH:n repoihin
- Tyhjennä Maven cache: `mvn dependency:purge-local-repository`

**Palvelu ei käynnisty:**
- Tarkista että portti 8080 on vapaana
- Tarkista että määritetyt ePerusteet-palvelut ovat saavutettavissa
- Tarkista lokitiedostosta virheilmoitukset

**PDF-generointi epäonnistuu:**
- Varmista että FOP-konfiguraatio löytyy määritetystä poluista
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

- [Palvelukortti](https://wiki.eduuni.fi/display/ophpolku/ePerusteet+palvelukokonaisuus) - Yleiskatsaus palveluun

### 6.2. Lisenssi

Katso LICENSE.txt

### 6.3. Yhteystiedot

Opetushallitus / ePerusteet-tiimi

