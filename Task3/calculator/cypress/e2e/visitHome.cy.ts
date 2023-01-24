// https://docs.cypress.io/api/introduction/api.html

describe("Tests for visiting root", () => {
  it("visits the app root url and checks titles", () => {
    cy.visit("/");
    cy.contains("h1", "Calculator");
    cy.contains("h4", "This is a simple calculator")
  });

  it("checks navbar links at /", () => {
    cy.visit("/");
    cy.contains("a", "Calculator").should("have.css", "color", "rgb(66, 185, 131)");
    cy.contains("a", "Feedback").should("have.css", "color", "rgb(44, 62, 80)");
  })
});
