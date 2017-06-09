# LogRedaction
Java program  for log redaction (remove credit card and SSN).

Scenario: One of our customers has been inadvertently uploading sensitive personally-identifying information (PII) to our system over a period of several months. 
The customer has since realized their mistake and removed the data from the system, but some of that information was reflected in debugging logs enabled on the system and will need to be removed. 
The logs in question are archived to a central location and compressed with the gzip utility.
