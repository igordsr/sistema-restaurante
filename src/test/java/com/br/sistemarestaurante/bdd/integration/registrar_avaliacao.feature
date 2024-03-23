gherkin
Feature: Registrar avaliação
  Como um cliente
  Eu quero registrar uma avaliação
  Para que eu possa avaliar a minha experiência

  Scenario: Registrar uma nova avaliação e verificar se os dados estão corretos
    Given Eu tenho uma avaliação válida
    When Eu registro a avaliação
    Then A avaliação registrada deve ter os mesmos dados da avaliação enviada