@shop
Feature: SatinAlma

  Background: Url and close
    Given Kullanici URL e gider
  Scenario: Satin Alma
    When Kullanici arama kutusuna urun yazar ve arama yapar
    Then Kullanici bes urun ekler
    Then Kullanici sepetinize eklenmistir yazisini gorur
    Then Kullanici sepette bes urun oldugunu kontrol eder
    And Uygulamayi kapat







