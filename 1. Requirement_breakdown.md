### What questions would you ask to understand the requirement better?

## Functional Requirements

1. What is the target audience for the application?
2. Is this a standalone application or Embedded in to an existing app ?
3. Will the application be accessible on desktop and mobile devices?
4. What are the design and branding guidelines that the application needs to follow?
5. What are the tracking and analytics requirements for the application?
6. What are geographical restrictions for the application?
7. What user data is collected ? Is it stored in a database or in a cookie?
8. Cookie consent is required for EU citizens. Is this a requirement for the application?
9. What is the expected time frame for the application to be completed?
10. Which desktop and mobile browsers and versions does this application needs to support ?
11. What are the accessibility requirements for the application?
12. What are the localization requirements for the application?
13. Will the application allow users to search for one-way or round-trip flights?
14. Will the application allow users to search for flights between two specific locations, or will it allow for more flexible search criteria such as "anywhere in Europe"?
15. Will the application allow users to specify layover locations or flight duration?
16. Will the application allow users to search for flights based on specific dates, or will it allow for more flexible date ranges such as "anytime in the next 6 months"?
17. Will the application allow users to search for flights with multiple passengers, and if so, will it allow them to specify the ages of any minors traveling with them?
18. Will the application allow users to book flights directly through the application, or will it  redirect users to other websites to complete their bookings?
19. Will the application allow users to save their flight search criteria and receive email notifications when prices change?
20. Does the search results are displayed in a paginated format or infinite scroll ? Does it change based on the screen size ?
21. What are the filters that can be applied to the search results ?

## Non-Functional Requirements

1. Who are the relevant stakeholders for the application?
2. What are the performance requirements (Response time, throughput, availability) for the application ?
3. What are the scalability requirements for the application?
4. Is there any security requirements like  [OWASP Top 10](https://owasp.org/www-project-top-ten/) ?
5. Where does the application retrieve flight data? Is it a third-party API, or is it a custom database? How to Authenticate to the API ? Is there any rate limiting ?
6. How often does the application retrieve flight data? Is caching required?
7. What are the data storage requirements for the application?
8. What are the data backup, retention, deletion requirements for the application?
9. Is there any preferred tech stack for Frontend, Backend, UI, UX, Database, CI/CD, Monitoring, Logging, etc ?
10. What is the preferred deployment method for the application? Cloud/On-premise ?

### How would you define an appropriate Minimum Viable Product (MVP) ?

When defining an MVP, it is important to focus on the core functionality of the product and prioritize the 
features that are most important to the user. This may involve cutting out non-essential features 
or simplifying the user experience in order to get the product to market faster.
An MVP should be designed to provide the most value to the user with the least amount of effort, 
in order to gather the most useful data and feedback as quickly as possible.

For the above example, the MVP could be a simple but an extensible web application with the following features:
1. A search form that allows users to enter their departure and arrival locations, and select their preferred dates of travel.
2. A results page that displays a list of available flights based on the search criteria entered by the user.
3. The ability for users to filter the results by time, price, and flight duration.


