describe("Tests for trying out form for feedback", () => {
    beforeEach(() => {
        cy.visit("/feedback")
    })

    it("Feedback button links to /feedback", () => {
        cy.visit("/")
        cy.contains("a", "Feedback").click()
        cy.url().should("include", "/feedback")
        cy.contains("Send us your feedback")
    })

    it("Submitting without filling form shows error", () => {
        cy.get("form").submit()
        cy.contains("#error-message", "Name is required")
        cy.contains("#error-message", "Email is required")
        cy.contains("#error-message", "Message is required")
    })

    it("Email field has to be a valid email", () => {
        cy.get("input[id='Name']").type("example name")
        cy.get("input[id='Email']").type("invalid email")
        cy.get("textarea[id='Message']").type("example message")
        cy.get("form").submit()
        cy.contains("#error-message", "Not a valid email")
    })

    it("Submitting form shows network error message when server is offline", () => {
        cy.get("input[id='Name']").type("example name")
        cy.get("input[id='Email']").type("exampleEmail@example.com")
        cy.get("textarea[id='Message']").type("example message")
        cy.get("form").submit()
        cy.contains("#submit-message", "Something went wrong. Please try again later.")
    })

    it("Submitting form shows success message when server is online", () => {
        cy.intercept("http://localhost:3000/data/", {
            statusCode: 201,
        })
        cy.get("input[id='Name']").type("example name")
        cy.get("input[id='Email']").type("exampleEmail@example.com")
        cy.get("textarea[id='Message']").type("example message")
        cy.get("form").submit()
        cy.contains("#submit-message", "Thanks for your feedback!")

    })
})