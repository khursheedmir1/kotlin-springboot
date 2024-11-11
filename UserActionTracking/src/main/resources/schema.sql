CREATE TABLE UserActions
(
    actionId        STRING(36) NOT NULL,
    userId          STRING(255),
    sessionId       STRING(255),
    traceId         STRING(255),
    globalAddressId STRING(255),
    ipAddress       STRING(45),
    timestamp       TIMESTAMP NOT NULL,
    actionType      STRING(100) NOT NULL,
    currentStep     STRING(100),
    selectionData   JSON,
    errorCode       STRING(50),
    errorMessage    STRING(1000)
) PRIMARY KEY (actionId);