# GitHub Testing Automation Project

## 1. Introduction

### 1.1 About the Project
This project involves testing the GitHub web application, focusing on both core and advanced functionalities using Selenium and JUnit in Java. GitHub serves as a platform for version control, collaboration, and repository management, offering tools to manage code, track issues, and collaborate effectively.

A comprehensive QA automation suite was implemented to evaluate GitHub's performance and reliability. The suite includes automated tests for:
- User authentication
- Form submissions
- Search functionality
- Navigation features

Selenium WebDriver was used for UI interactions, while JUnit was employed for organizing and running the tests.

**Website:** [GitHub](https://www.github.com)

### 1.2 Project Functionalities
Main features of GitHub tested include:
- **User Authentication**: Login, registration, and session handling.
- **Repository Management**: Creation, deletion, and updates.
- **Search and Filters**: Searching for users, repositories, and topics with sorting and filtering capabilities.
- **Pagination**: Verifying functionality and accuracy of paginated content.
- **Navigation and Links**: Ensuring seamless functionality for sidebar, header, and footer links.
- **User Profile Customization**: Updating profiles and repository visibility settings.
- **HTTPS Enforcement**: Verifying secure connections.
- **Accessibility and Performance Testing**: Ensuring compliance with WCAG guidelines and reliability under load.
- **Security Testing**: Verifying secure data handling and session management.

## 2. Project Architecture

The project adopts the **Page Object Model (POM)** design pattern to enhance modularity, reusability, and maintainability of the test suite.

### Key Elements:
- **Page Classes**: Encapsulate locators and methods for interacting with page elements.
- **Test Classes**: Organized by functionality (e.g., `LoginTests`, `SearchTests`).
- **Utility Classes**: Provide shared resources, including configuration files.
- **Base Class**: Manages WebDriver initialization, setup, and teardown.

## 3. Testing Tools and Environment

### Environment:
- **Operating System**: Windows 11 Pro
- **Browser**: Chrome (Version 117+)

### Tools and Frameworks:
- **Programming Language**: Java
- **Code Editor**: IntelliJ IDEA Community Edition
- **Testing Framework**: Selenium Framework with JUnit
- **Browser Driver Management**: WebDriverManager (Chrome)

## 4. Test Summary

### 4.1 Testing Overview
| **Testing Tool**      | **Total Tests** | **Passed Tests** | **Failed Tests** |
|------------------------|-----------------|------------------|------------------|
| JUnit + Selenium       | ~49–50         | ~47–48           | ~1–2             |

#### Failed Tests:
1. **Accessibility Test**: All links should contain text (missing text content).
2. **SSL Certificate Test**: Intermittent page load failures caused inconsistent test results.

### 4.2 Final Thoughts
Testing GitHub's functionalities was challenging due to:
- **Element Identification**: Many elements lacked `id` attributes, requiring complex locators.
- **Rate Limiting**: Introduced random wait times up to 5 seconds between requests.

Despite these challenges, the majority of tests (~47–48 out of ~49–50) passed, highlighting GitHub's reliability in core functionalities. Areas for improvement include accessibility, error handling, and support for automated testing.

## 5. Tests Conducted

### 5.1 Key Tests:
- **Login Tests**: Validate secure and accurate user authentication.
- **Repository Tests**: Test CRUD operations and error handling.
- **Search Tests**: Validate sorting, filtering, and accuracy of search results.
- **Navigation Tests**: Ensure proper functionality of navigation links.
- **Accessibility Tests**: Verify compliance with accessibility standards.
- **Performance Tests**: Ensure responsiveness under load.
- **Security Tests**: Validate secure data handling.
- **Pagination Tests**: Ensure seamless pagination.

### 5.2 Additional Tests:
- **Footer Links Tests**: Ensure footer navigation links work correctly.
- **Title Tests**: Verify correct page titles for major GitHub pages.

## 6. Documentation

For a detailed report on test execution and findings, refer to the full documentation [Test Documentation](test_documentation.pdf).

---
