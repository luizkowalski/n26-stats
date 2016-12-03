# N26 Challenge
Small service to aggregate transactions and provide statistics about them.
Only transactions from last minute are taken into account.


### Implementation
I used `ConcurrentNavigableMap` as data store. It allows me to navigate through the list using the timestamp as key.
The method `tailMap` will return a submap with transactions grouped by timestamp where they key is >= the timestamp specified.
Aggregation is done using `DoubleSummaryStatistics` already provided by Java 8 API.

### Running
To run it, you can use
```java
mvn spring-boot:run
```

The endpoints will be available at `http://127.0.0.1:8080/`

### Testing
To run the test suit, execute
```java
mvn test
```

