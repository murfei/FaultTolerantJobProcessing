## JoberstellungTest.invalidPayloadTest() (Z-1)
```log
Starte Versuch: 1 für Aufruf: jobClient.service.JobClient$$Lambda/0x0000025401eb0000
Starte Versuch: 1 für Aufruf: backend.api.JobController$$Lambda/0x0000025401f19e60
Anzahl Jobs vor dem Test: 0
Starte Versuch: 1 für Aufruf: jobClient.service.JobClient$$Lambda/0x0000025401f3a9a8
2026-08-24T18:05:33.734+02:00  WARN 7096 --- [JobProcessing] [nio-8080-exec-2] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public org.springframework.http.ResponseEntity<backend.api.CreateJobResponse> backend.api.JobController.createJob(backend.api.CreateJobRequest): [Field error in object 'createJobRequest' on field 'payload': rejected value []; codes [NotBlank.createJobRequest.payload,NotBlank.payload,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [createJobRequest.payload,payload]; arguments []; default message [payload]]; default message [darf nicht leer sein]] ]
Policy lehnt einen neuen Verarbeitungsversuch für Aufruf: jobClient.service.JobClient$$Lambda/0x0000025401f3a9a8 ab.
Letzter Versuch scheiterte an: 400 BAD_REQUEST: Fehler bei Anfrage der Job-Processing Backend-API. Retry nicht sinnvoll.
Der Job konnte nicht erfolgreich zugestellt werden. Der letzte Fehler war: 400 BAD_REQUEST: Fehler bei Anfrage der Job-Processing Backend-API. Retry nicht sinnvoll.
Starte Versuch: 1 für Aufruf: jobClient.service.JobClient$$Lambda/0x0000025401f3a9a8
PayloadValidator: Payload ist ungültig
Policy lehnt einen neuen Verarbeitungsversuch für Aufruf: jobClient.service.JobClient$$Lambda/0x0000025401f3a9a8 ab.
Letzter Versuch scheiterte an: 400 BAD_REQUEST: Fehler bei Anfrage der Job-Processing Backend-API. Retry nicht sinnvoll.
Der Job konnte nicht erfolgreich zugestellt werden. Der letzte Fehler war: 400 BAD_REQUEST: Fehler bei Anfrage der Job-Processing Backend-API. Retry nicht sinnvoll.
Starte Versuch: 1 für Aufruf: jobClient.service.JobClient$$Lambda/0x0000025401eb0000
Starte Versuch: 1 für Aufruf: backend.api.JobController$$Lambda/0x0000025401f19e60
Anzahl Jobs nach dem Test: 0
```

## JoberstellungTest.duplicateRequestTest() (Z-2)
```log
Anzahl Jobs vor dem Test: 0
Starte Versuch: 1 für Aufruf: jobClient.service.JobClient$$Lambda/0x000001d721ec08d8
Starte Versuch: 1 für Aufruf: backend.api.JobController$$Lambda/0x000001d721f29f88
Warte 200ms bis zum nächsten Retry...
Starte Versuch: 2 für Aufruf: jobClient.service.JobClient$$Lambda/0x000001d721ec08d8
Starte Versuch: 1 für Aufruf: backend.api.JobController$$Lambda/0x000001d721f29f88
2026-08-24T18:12:29.028+02:00  WARN 29324 --- [JobProcessing] [nio-8080-exec-2] org.hibernate.orm.jdbc.error             : HHH000247: ErrorCode: 0, SQLState: 23505
2026-08-24T18:12:29.028+02:00  WARN 29324 --- [JobProcessing] [nio-8080-exec-2] org.hibernate.orm.jdbc.error             : ERROR: duplicate key value violates unique constraint "jobs_idempotency_key_key"
Detail: Key (idempotency_key)=(7771e1fd-d2ab-4c40-b801-fff4256c7b02) already exists.
Policy lehnt einen neuen Verarbeitungsversuch für Aufruf: backend.api.JobController$$Lambda/0x000001d721f29f88 ab.
Letzter Versuch scheiterte an: could not execute statement [ERROR: duplicate key value violates unique constraint "jobs_idempotency_key_key"
Detail: Key (idempotency_key)=(7771e1fd-d2ab-4c40-b801-fff4256c7b02) already exists.] [insert into jobs (attempt_count,claimed_by,created_at,idempotency_key,lease_until,payload,status,updated_at,id) values (?,?,?,?,?,?,?,?,?)]; SQL [insert into jobs (attempt_count,claimed_by,created_at,idempotency_key,lease_until,payload,status,updated_at,id) values (?,?,?,?,?,?,?,?,?)]; constraint [jobs_idempotency_key_key]
Statuscode: 200 OK
Anzahl Jobs nach dem Test: 1
```

## ConcurrencyTest.onlyOneWorkerClaimsContendedJob() (Z-3 (a))
```log
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 58c5f4fc-f3c2-438f-91d0-94b986c2941d
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job df272db6-5891-464d-8aec-2263aa580a56
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 95e0b6a3-2946-4d93-a66a-162dfe55bb0a
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job bb391639-7813-48e0-acb9-212f2f133b2a
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job cc8f037c-aa98-4328-b12f-546b535c233a
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 37df000e-e5f6-4b19-b656-ad7f5738104a
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job cbce9a8d-92ff-467e-a126-e20837a7d416
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 583f5d76-2b4c-4851-af59-3158c82fdd11
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d6668db3-7c95-45ab-b14c-9b00fb96da25
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 3b7df8ac-c34c-4a47-9980-0c096079fa16
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job b9f1b4c9-02c6-4020-a4fa-c642e675c9db
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job d5d9c6a9-120e-44ab-93f0-1c35bed4bf42
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 8bae55f7-9e95-40a6-ae54-b2e81b98b150
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2e860023-6b80-4690-8810-2044b4fbe5cb
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 434e043b-b37e-4acd-a188-c3a955b3308c
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 684d9c8c-e425-47ae-99ae-930fef2a4d6e
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 3b3516f5-28ff-4257-a627-dfb28a6a8dd3
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job db600a49-3ba9-4852-8acc-616678620d0c
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 8e7bd4e7-2ec7-473d-a8e9-217cbe1b7878
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 42528f92-f3c8-4561-b8b6-b06fe316da4f
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 892417d0-620b-456c-a90a-8f413a2557c6
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 379a8573-6087-42cd-8296-4e96a8abf8b3
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 45008ebd-278e-46cf-bfb0-81811f17fbf8
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 597e6680-1408-4e18-9c3b-8ca49049652f
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 6b37fa82-08a3-41ec-ae77-9a8dec2cbb7b
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 6b88b0a2-1592-411f-9db4-e006c532ea51
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 8dc81922-2d7e-47b8-9f52-28f580b412ef
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 766e44a2-075a-4459-b9f1-1df2515dc76d
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job d8ada5b6-8678-4165-bed4-220b7d479c59
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job cc37d62e-c637-4905-944f-f0fee229e640
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 383e2064-7dcd-4791-a857-eb475e70d5ee
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job d0d7e5fa-c557-498a-81fd-9824768ac39c
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job a9eab63f-3f0f-433d-bed4-2fd43d058698
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job ff5ad177-1657-4fd5-a66c-77bd690c1e1e
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job db38e8bd-3c6f-46b2-ab17-83dd6a09b5f8
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 6df9e086-37f6-479e-af27-cc40f5194bfa
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 5bb9a58a-494e-495b-82a4-9389721f6d77
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 0a42f2a0-fdcb-46e7-8a64-51be30806742
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job d95c6892-b6fb-4641-b97e-85fc5388e5cc
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 441e7320-0998-4c21-b977-aa5dfee3da75
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 101b1eea-ccbd-434a-b029-bae831dfd048
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 6533f7f6-7633-4036-a434-6c9cc813d99b
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9a53b4ec-004f-4edb-bb9b-f1e39cf1efab
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job f1de5c7c-69ba-4a80-acb5-56a23a91cd27
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2847c441-ab69-44a0-bcc7-1b69ef1d3483
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 34869681-b408-4237-8d9f-96e050220199
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7a1bbaad-8f0a-440c-9de6-30ac2bd66dbc
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job ee6c76e8-77ac-4644-a25b-2304a8470562
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1d512bcb-c2bd-4771-9322-93b238cefa24
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2690563f-708e-4978-956b-cab967039106
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job adc0134a-93ae-491f-a98f-da84e704cc58
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 30b7dfc3-b609-4427-a658-da93e8ca0be6
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1b7f9fef-2605-4081-be28-275a007414ac
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job bb5e5e0e-2808-4633-801f-eadf4693f6cc
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 48eeb9ff-acd0-46ba-af65-9550f6766780
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9c99ced2-d9de-4584-a343-9f8a41d11f3d
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job ca3aa962-7996-4d38-9f33-7df68638ec17
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job e9084981-f3c4-4c2f-85fc-d4fb9e1f87c1
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job cdc825ab-255a-4447-9f22-a60e0d8da6bd
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job e430dd74-4e31-4255-af27-58fd3f325c6f
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 847e0feb-23a1-495f-8c3a-68e06b140ac5
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 84e9179a-79b6-4a05-b331-2d3306733964
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job e050c254-5c63-42af-a5dd-416131e6f518
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 4253a28d-dc31-432a-b29c-17ef44263029
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job c453d062-5ec8-41e8-ae5a-5e38115830f6
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job be35eedf-7c41-4e7d-91fa-dc2a4f76d2f8
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job c89bca36-36d1-4d19-ac80-ac69ec2da693
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d1985cbb-7ebc-4785-adee-442c5a4bdb05
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 0f40f812-ff00-4649-a050-899281c5685f
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9c6231fe-341e-4c17-85ca-61ec727465b7
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job cadae26b-01fb-49df-bfe4-9e4dd9cc00b9
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job d36152fe-b0ac-427a-830b-95170c3838a4
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 637890b2-832c-441b-bf2b-77a83addae0e
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1d84dfce-3603-445f-8bbd-6ba5926a333c
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c829005c-8226-44aa-9562-8eb3c19cc734
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job ab5b7a1e-5409-4fb4-b50a-1b4ba572254d
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job ba36b609-e1a8-4dee-94d9-9d3056ba0d77
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 5daecb5c-2dd2-4463-ae8e-78df92471d28
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 89222ea5-0beb-435b-b647-41e988658b89
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 4fd0f947-818e-45b4-a589-7c555d69bd47
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 3e58bcd2-7933-4416-a3ce-441f7eb7cb2b
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job ffab00bf-f80d-4afc-bdfd-673230ec1b85
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1d540b90-1cfb-4df5-b8e2-f6a2457db2b1
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job b35394d3-273a-4e9d-90e5-4247e8b9dd89
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 5d43ab12-94e1-4763-a378-fda66eaef7d9
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 728777fd-d8e4-459d-8200-d7366f95d69c
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1bf3e1cd-3a35-45cd-bbfb-628bff4584af
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9b275263-46c1-48fa-9b46-aeb8d9f7413b
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9b6d333e-4cf8-45cf-87da-4cacdbe0e6b4
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job a3aff410-3132-4a85-96e3-b3be258e868e
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 90a123c3-2031-4f57-9ee7-fc6b6afe18f1
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 43eeafd3-ab35-4c5a-a034-6a5fda53287e
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job a85fb5a4-1431-4cad-aec1-91c644ed3a76
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 59bbf1be-7495-49ad-a631-16c62381af1f
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7155da52-b13f-4d8b-9c7a-695071aeb5c4
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job e28ec313-1c10-4c64-8c94-101feea202d6
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 0cca908f-3169-4d98-8e8c-e2977947584a
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1a65077f-5b1c-41ab-abcb-2773d4a14dac
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job ce171b73-448b-48aa-9ce9-f3b5cf353274
Kein Job gefunden
---------------------------------------Testdurchlauf---------------------------------------
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Kein Job gefunden
Job: Zustandsübergang von QUEUED nach RUNNING für Job 25f52166-ead8-4ce3-92f1-08b58c9f3c21
Kein Job gefunden
```

## ConcurrencyTest.concurrentFinishAttemptsYieldExactlyOneStoredResult() (Z-3 (b))
```log
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2e8ded50-a651-4653-8b19-63c484881bdc
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 2e8ded50-a651-4653-8b19-63c484881bdc
Job 2e8ded50-a651-4653-8b19-63c484881bdc erfolgreich beendet durch Worker f7b71f3e-5529-447b-8199-8f3023c1aab9
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 233d68c9-8e2c-4617-9d40-ddc74332bd94
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: bb6f5dc3-15e0-4a19-b81a-8a8225973d72 Worker: 28410689-8325-4593-9981-5a9bf0aacb96
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 233d68c9-8e2c-4617-9d40-ddc74332bd94
Job 233d68c9-8e2c-4617-9d40-ddc74332bd94 erfolgreich beendet durch Worker bb6f5dc3-15e0-4a19-b81a-8a8225973d72
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 42601828-3d20-48f1-be18-2cd245349c76
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 42601828-3d20-48f1-be18-2cd245349c76
Job 42601828-3d20-48f1-be18-2cd245349c76 erfolgreich beendet durch Worker 7645882a-6d5b-4a3c-8df1-453377756d39
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job b217cb98-5299-47ec-85b3-324ed3e940e1
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: b49c65dc-23c9-4e2d-8e16-01d97834ee68 Worker: c1a87322-6637-4774-867a-8a3e74872941
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job b217cb98-5299-47ec-85b3-324ed3e940e1
Job b217cb98-5299-47ec-85b3-324ed3e940e1 erfolgreich beendet durch Worker b49c65dc-23c9-4e2d-8e16-01d97834ee68
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job bab178ea-b87c-4b65-99ce-93ef1ef34963
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job bab178ea-b87c-4b65-99ce-93ef1ef34963
Job bab178ea-b87c-4b65-99ce-93ef1ef34963 erfolgreich beendet durch Worker c65a366b-615e-4a73-9597-0c5c40825879
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 695c96d3-a58f-4f01-b0b2-385308476b4e
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 695c96d3-a58f-4f01-b0b2-385308476b4e
Job 695c96d3-a58f-4f01-b0b2-385308476b4e erfolgreich beendet durch Worker 7e750fe2-98ca-4cb1-90e7-c6533145af95
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 24e5c147-1d89-4a38-8f5b-ae9d244cf5c0
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: cb6993e4-e786-494e-ad0d-6fda58fdbe7a Worker: 96ec7d92-20f7-4174-b04d-6d5e96ed8a07
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 24e5c147-1d89-4a38-8f5b-ae9d244cf5c0
Job 24e5c147-1d89-4a38-8f5b-ae9d244cf5c0 erfolgreich beendet durch Worker cb6993e4-e786-494e-ad0d-6fda58fdbe7a
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job fd9f26f2-f707-4fc3-b00a-b5ffeac509e1
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 86b1b985-9e35-4f7c-8bce-9fd043a96a9d Worker: d2a68d2a-6ebc-43cd-85c6-cbbb113e1194
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job fd9f26f2-f707-4fc3-b00a-b5ffeac509e1
Job fd9f26f2-f707-4fc3-b00a-b5ffeac509e1 erfolgreich beendet durch Worker 86b1b985-9e35-4f7c-8bce-9fd043a96a9d
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job b8bd903d-3b9d-4a75-be8c-5dfafa7485b8
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: aecf332b-d0d4-4461-ace7-fec3b36375d1 Worker: 8b3e34d0-4f3d-4357-8ccb-90950ce16736
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job b8bd903d-3b9d-4a75-be8c-5dfafa7485b8
Job b8bd903d-3b9d-4a75-be8c-5dfafa7485b8 erfolgreich beendet durch Worker aecf332b-d0d4-4461-ace7-fec3b36375d1
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 8766ef62-ac16-42bd-84dc-bb4f4ed17bcd
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 76ac84d7-fd7e-44a9-8d32-6fedaf440a52 Worker: 1e9e7306-322a-4ac8-ab0b-59335a94ec35
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 8766ef62-ac16-42bd-84dc-bb4f4ed17bcd
Job 8766ef62-ac16-42bd-84dc-bb4f4ed17bcd erfolgreich beendet durch Worker 76ac84d7-fd7e-44a9-8d32-6fedaf440a52
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 301dcb81-0fa2-4f81-8eb8-59a89fade6d7
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: f44debf9-4c46-4fa8-9ce5-6c6c301ee891 Worker: e58c5d40-f2a1-486c-aac5-c6cd25e3d31a
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 301dcb81-0fa2-4f81-8eb8-59a89fade6d7
Job 301dcb81-0fa2-4f81-8eb8-59a89fade6d7 erfolgreich beendet durch Worker f44debf9-4c46-4fa8-9ce5-6c6c301ee891
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9fbdf01e-1804-4517-9f0e-bd574d438efa
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: eb81d7f8-0ac2-43f9-b114-a1972b4a6bda Worker: 8b5f59d9-6d72-445f-9f35-70785fdc28e9
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 9fbdf01e-1804-4517-9f0e-bd574d438efa
Job 9fbdf01e-1804-4517-9f0e-bd574d438efa erfolgreich beendet durch Worker eb81d7f8-0ac2-43f9-b114-a1972b4a6bda
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 62b95355-edae-4265-a314-7ec73a12ef28
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 9a9d28ae-2114-4999-9555-ce479f622988 Worker: a6436083-e57d-4b64-b508-293d67892ff9
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 62b95355-edae-4265-a314-7ec73a12ef28
Job 62b95355-edae-4265-a314-7ec73a12ef28 erfolgreich beendet durch Worker 9a9d28ae-2114-4999-9555-ce479f622988
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 01dde77d-8d36-4a84-ab32-421ecff364d2
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 2c69b3a7-58d9-43c7-8ebf-991253ce0a68 Worker: c9b35fda-de26-410a-b1dd-ca38aa60bbec
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 01dde77d-8d36-4a84-ab32-421ecff364d2
Job 01dde77d-8d36-4a84-ab32-421ecff364d2 erfolgreich beendet durch Worker 2c69b3a7-58d9-43c7-8ebf-991253ce0a68
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job e432a471-5443-4122-b568-bf127d7d9c60
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: c39ff5cf-f1ed-403d-b045-a521d52a3d41 Worker: 31af7847-61a1-4ac0-8882-17878503e018
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job e432a471-5443-4122-b568-bf127d7d9c60
Job e432a471-5443-4122-b568-bf127d7d9c60 erfolgreich beendet durch Worker c39ff5cf-f1ed-403d-b045-a521d52a3d41
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2f1cc3af-1b60-4e8c-a52c-251bd06963f0
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 1fec0760-8f51-4932-8482-0d1ac0e8f4d2 Worker: b4f56c4f-0ee7-461e-820a-a26d783bf930
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 2f1cc3af-1b60-4e8c-a52c-251bd06963f0
Job 2f1cc3af-1b60-4e8c-a52c-251bd06963f0 erfolgreich beendet durch Worker 1fec0760-8f51-4932-8482-0d1ac0e8f4d2
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 704fe6b0-1e20-4608-a108-e22a781e8047
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 704fe6b0-1e20-4608-a108-e22a781e8047
Job 704fe6b0-1e20-4608-a108-e22a781e8047 erfolgreich beendet durch Worker 46aa7d1e-939c-4906-9fed-a41ee7b81545
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9641723f-99ee-47c3-a8ab-979ab4986a6a
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 9641723f-99ee-47c3-a8ab-979ab4986a6a
Job 9641723f-99ee-47c3-a8ab-979ab4986a6a erfolgreich beendet durch Worker b69b6f8e-ce00-4d75-bf40-c8085a076997
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 8282ab5d-19d1-424e-9ccb-70d73d12f1b9
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 8282ab5d-19d1-424e-9ccb-70d73d12f1b9
Job 8282ab5d-19d1-424e-9ccb-70d73d12f1b9 erfolgreich beendet durch Worker b9fdfee2-65fd-4e78-b015-7e5f8e044873
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d6e88fe6-3f79-4ce0-b229-35f6c4700241
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job d6e88fe6-3f79-4ce0-b229-35f6c4700241
Job d6e88fe6-3f79-4ce0-b229-35f6c4700241 erfolgreich beendet durch Worker e4617077-d104-49f9-8f44-7350d86f48e1
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 006a847f-b5e3-4d28-8958-7a3cecfef847
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: cd4b1ae2-cab1-4daf-abf1-5784ecac6b8c Worker: a43df4d8-1050-45d8-a032-e3b10ce9c5ac
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 006a847f-b5e3-4d28-8958-7a3cecfef847
Job 006a847f-b5e3-4d28-8958-7a3cecfef847 erfolgreich beendet durch Worker cd4b1ae2-cab1-4daf-abf1-5784ecac6b8c
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job cbf8b256-034c-4e92-8ef1-2a64e4091e13
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job cbf8b256-034c-4e92-8ef1-2a64e4091e13
Job cbf8b256-034c-4e92-8ef1-2a64e4091e13 erfolgreich beendet durch Worker 2c7accdd-c4a0-4ebe-b3ea-3f579db8df4e
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9eb1f267-cc08-4014-9680-916568afa133
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: dd675a52-a25a-4127-9fc9-dd63c93d3b40 Worker: aa2b9866-55e3-42e5-bacd-5982c1c752c4
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 9eb1f267-cc08-4014-9680-916568afa133
Job 9eb1f267-cc08-4014-9680-916568afa133 erfolgreich beendet durch Worker dd675a52-a25a-4127-9fc9-dd63c93d3b40
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7808500f-d9cb-4f04-9e9d-72ab000dd458
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 7808500f-d9cb-4f04-9e9d-72ab000dd458
Job 7808500f-d9cb-4f04-9e9d-72ab000dd458 erfolgreich beendet durch Worker 7cbe1fdc-dda6-419f-9d21-bbb0969b81a5
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 5a7b370d-33bb-43d8-aab5-b525ec54eca7
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 5a7b370d-33bb-43d8-aab5-b525ec54eca7
Job 5a7b370d-33bb-43d8-aab5-b525ec54eca7 erfolgreich beendet durch Worker d0045c05-01d3-4237-b1fa-a3934e25a43e
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 81f4ad89-a635-4c96-805d-046e433196f5
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 889a1367-a753-4ca4-8d62-5aed233555d1 Worker: 48a9a440-aba5-4b0e-9e0e-0f9e160e261a
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 81f4ad89-a635-4c96-805d-046e433196f5
Job 81f4ad89-a635-4c96-805d-046e433196f5 erfolgreich beendet durch Worker 889a1367-a753-4ca4-8d62-5aed233555d1
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job acf4c2d5-07e9-4526-bf57-a706327b4e8e
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 30157b46-5260-4d8b-ae9a-9cdcd6a04e7a Worker: cb71c837-5967-417d-b5a3-c2597ddb7898
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job acf4c2d5-07e9-4526-bf57-a706327b4e8e
Job acf4c2d5-07e9-4526-bf57-a706327b4e8e erfolgreich beendet durch Worker 30157b46-5260-4d8b-ae9a-9cdcd6a04e7a
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2f8401d9-daa9-4e46-ba05-1123e93fd234
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 2f8401d9-daa9-4e46-ba05-1123e93fd234
Job 2f8401d9-daa9-4e46-ba05-1123e93fd234 erfolgreich beendet durch Worker f82a1133-b685-40da-a559-ba7c8bfbc2b9
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 3c66ccd8-6f79-4196-9185-20975f1018fb
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 3c66ccd8-6f79-4196-9185-20975f1018fb
Job 3c66ccd8-6f79-4196-9185-20975f1018fb erfolgreich beendet durch Worker 5f61fa2d-439b-4358-84cf-2a0a236e2b8b
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 017dbf65-cd15-41dc-9fb8-bfa55fa98973
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 017dbf65-cd15-41dc-9fb8-bfa55fa98973
Job 017dbf65-cd15-41dc-9fb8-bfa55fa98973 erfolgreich beendet durch Worker 6c5eb61a-75cf-445e-b723-6b32febf5f50
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 288ba318-ba2f-4aee-9c73-225770d1944c
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 288ba318-ba2f-4aee-9c73-225770d1944c
Job 288ba318-ba2f-4aee-9c73-225770d1944c erfolgreich beendet durch Worker 5cf14312-b192-457c-814d-c23085e4e3b6
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job be722dcb-8dd6-4489-8b1b-009f7f7e2906
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 9ae6f390-572b-42af-9b9e-85b2b8d34859 Worker: ec6f334c-2bc2-4722-beef-ca630a0c406a
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job be722dcb-8dd6-4489-8b1b-009f7f7e2906
Job be722dcb-8dd6-4489-8b1b-009f7f7e2906 erfolgreich beendet durch Worker 9ae6f390-572b-42af-9b9e-85b2b8d34859
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job e9a1dbee-0299-4ac9-801f-39ee0972cd37
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 714eae4a-6f92-44f1-8a7e-4043f6adee3d Worker: 3004ba40-fae8-43be-823d-713a0339b5dc
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job e9a1dbee-0299-4ac9-801f-39ee0972cd37
Job e9a1dbee-0299-4ac9-801f-39ee0972cd37 erfolgreich beendet durch Worker 714eae4a-6f92-44f1-8a7e-4043f6adee3d
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job bd4e958f-b604-45fe-af5a-29d70d812827
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job bd4e958f-b604-45fe-af5a-29d70d812827
Job bd4e958f-b604-45fe-af5a-29d70d812827 erfolgreich beendet durch Worker a789bf29-0698-4c32-92e2-8c0128778947
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9d01924f-ceb9-48be-8211-17382073f59d
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 9d01924f-ceb9-48be-8211-17382073f59d
Job 9d01924f-ceb9-48be-8211-17382073f59d erfolgreich beendet durch Worker a8e89874-2ae9-4ae5-bce5-8a8f14fa9cc7
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 47e5e888-d473-48cb-b71e-54b6389cc3ff
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 47e5e888-d473-48cb-b71e-54b6389cc3ff
Job 47e5e888-d473-48cb-b71e-54b6389cc3ff erfolgreich beendet durch Worker 2f846917-5a54-45d3-88dd-d8e6be72a739
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job b03f6822-2f60-47d9-8694-b01fa25328b8
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job b03f6822-2f60-47d9-8694-b01fa25328b8
Job b03f6822-2f60-47d9-8694-b01fa25328b8 erfolgreich beendet durch Worker ac15806b-3704-45b4-b8d2-c5daea55f126
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9d04b715-02de-464f-bde4-a74a96b29caf
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 9d04b715-02de-464f-bde4-a74a96b29caf
Job 9d04b715-02de-464f-bde4-a74a96b29caf erfolgreich beendet durch Worker 1897c785-03bf-407b-adfc-b91823c2ef4c
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 6bd3fb12-12f7-4262-bd97-12a62e18066b
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 6bd3fb12-12f7-4262-bd97-12a62e18066b
Job 6bd3fb12-12f7-4262-bd97-12a62e18066b erfolgreich beendet durch Worker ca435713-8ba8-475a-8f80-b4b167875b22
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 379fbe18-9a11-486e-afae-1622ccb444fb
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 379fbe18-9a11-486e-afae-1622ccb444fb
Job 379fbe18-9a11-486e-afae-1622ccb444fb erfolgreich beendet durch Worker d8e9c8f3-8db1-4890-b2b1-c751ccdc54a2
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1a32fe3a-6bcf-492b-a3ab-6d1b8db7647a
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 1a32fe3a-6bcf-492b-a3ab-6d1b8db7647a
Job 1a32fe3a-6bcf-492b-a3ab-6d1b8db7647a erfolgreich beendet durch Worker 6021d1e7-cecf-4c0e-82ec-78f6f8b58243
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job b9c018a8-87ce-4794-a911-ff3cc1f4ab7d
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 6e39308d-53a3-4c31-b745-4dfc3896afc1 Worker: 02bcbc4c-71ae-428a-9b0c-4ebb205812fb
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job b9c018a8-87ce-4794-a911-ff3cc1f4ab7d
Job b9c018a8-87ce-4794-a911-ff3cc1f4ab7d erfolgreich beendet durch Worker 6e39308d-53a3-4c31-b745-4dfc3896afc1
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 4acd0da0-baac-4638-bf1e-ed61ea6a83d6
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: e8fab9bb-4089-4709-b4c5-622c0d15fa9a Worker: 229b380a-3348-473d-aa17-8b2a8e451765
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 4acd0da0-baac-4638-bf1e-ed61ea6a83d6
Job 4acd0da0-baac-4638-bf1e-ed61ea6a83d6 erfolgreich beendet durch Worker e8fab9bb-4089-4709-b4c5-622c0d15fa9a
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 02dd21cb-6b40-47c5-bac1-6650fc8391d5
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 02dd21cb-6b40-47c5-bac1-6650fc8391d5
Job 02dd21cb-6b40-47c5-bac1-6650fc8391d5 erfolgreich beendet durch Worker 1961d1ee-9312-41f0-87e8-e77178ba5d6e
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job b697cc6b-90d4-46ef-a9c4-32700de84cda
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job b697cc6b-90d4-46ef-a9c4-32700de84cda
Job b697cc6b-90d4-46ef-a9c4-32700de84cda erfolgreich beendet durch Worker f5b185af-c6d8-4ec0-950f-f4330d5a04a2
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 82d2188e-83d9-4aa6-8c1e-f8e3252b91a4
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 82d2188e-83d9-4aa6-8c1e-f8e3252b91a4
Job 82d2188e-83d9-4aa6-8c1e-f8e3252b91a4 erfolgreich beendet durch Worker 1c87ead8-08e9-4714-b492-801ee93fb4f2
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job facaf0ea-e590-4878-b1fc-72db61f22e42
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: c5cc6b39-adb1-4c62-8ece-aa8eb8649549 Worker: 50e06419-d7ec-4df6-8f4f-ab118702a9bd
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job facaf0ea-e590-4878-b1fc-72db61f22e42
Job facaf0ea-e590-4878-b1fc-72db61f22e42 erfolgreich beendet durch Worker c5cc6b39-adb1-4c62-8ece-aa8eb8649549
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1bafab6f-d012-4a53-86db-2110c6307723
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 1bafab6f-d012-4a53-86db-2110c6307723
Job 1bafab6f-d012-4a53-86db-2110c6307723 erfolgreich beendet durch Worker 940a77ae-3cb0-42f2-8b91-a4be70cf5eee
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 0a671127-c31e-4fda-bad2-dbcbbab6a3ea
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 1c5f7e03-ae63-4683-bb5a-917342c901a9 Worker: 62a00389-6fb8-4c33-874e-be8f344aec19
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 0a671127-c31e-4fda-bad2-dbcbbab6a3ea
Job 0a671127-c31e-4fda-bad2-dbcbbab6a3ea erfolgreich beendet durch Worker 1c5f7e03-ae63-4683-bb5a-917342c901a9
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job f386414a-c16c-4579-8d58-3aa39ee98a19
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: f4ba416c-0c8e-4f4b-8cb8-78bb79566114 Worker: 69a17a5b-858f-46d0-a89e-b64221d799c4
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job f386414a-c16c-4579-8d58-3aa39ee98a19
Job f386414a-c16c-4579-8d58-3aa39ee98a19 erfolgreich beendet durch Worker f4ba416c-0c8e-4f4b-8cb8-78bb79566114
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job a3a5be69-583e-4604-a42a-881ed54b364d
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 238592b8-c736-4212-be99-51364262b590 Worker: f4e34d62-1d13-432e-8d6d-d2a37f79ad06
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job a3a5be69-583e-4604-a42a-881ed54b364d
Job a3a5be69-583e-4604-a42a-881ed54b364d erfolgreich beendet durch Worker 238592b8-c736-4212-be99-51364262b590
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2803dd7e-71be-4567-9849-aae781fe048e
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 2803dd7e-71be-4567-9849-aae781fe048e
Job 2803dd7e-71be-4567-9849-aae781fe048e erfolgreich beendet durch Worker 43c75a73-c20a-49c7-a3fd-3a08464c7967
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 53d7fd40-d061-413c-8cbb-4f98746bdf36
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: c752088f-fdb4-4014-bc04-7d7a729ddb2f Worker: b02da2f3-e250-4043-b8ba-42543cbcea13
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 53d7fd40-d061-413c-8cbb-4f98746bdf36
Job 53d7fd40-d061-413c-8cbb-4f98746bdf36 erfolgreich beendet durch Worker c752088f-fdb4-4014-bc04-7d7a729ddb2f
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 21edfdd5-0539-4bad-af95-a8becc0e6f28
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 21edfdd5-0539-4bad-af95-a8becc0e6f28
Job 21edfdd5-0539-4bad-af95-a8becc0e6f28 erfolgreich beendet durch Worker d425d281-0ad9-450c-bcf2-342e41c050e3
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job f306e6fb-8f86-4380-b808-9049bf3866b8
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job f306e6fb-8f86-4380-b808-9049bf3866b8
Job f306e6fb-8f86-4380-b808-9049bf3866b8 erfolgreich beendet durch Worker cdedc8a9-af2f-4fb9-8715-2bc5bbb4148e
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 4422fbbe-1432-48de-b297-52af66fd4974
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 4422fbbe-1432-48de-b297-52af66fd4974
Job 4422fbbe-1432-48de-b297-52af66fd4974 erfolgreich beendet durch Worker 4d28f63c-04d7-408c-8042-cc1b71632b46
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 3ddd08d8-ca2c-457c-b2c8-04516523f6f7
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 3ddd08d8-ca2c-457c-b2c8-04516523f6f7
Job 3ddd08d8-ca2c-457c-b2c8-04516523f6f7 erfolgreich beendet durch Worker 962a8535-dd86-4b8a-bdd5-3d0070b5e6f8
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job cc12a9d4-50d2-4ba2-9c1d-ccf945090b6c
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job cc12a9d4-50d2-4ba2-9c1d-ccf945090b6c
Job cc12a9d4-50d2-4ba2-9c1d-ccf945090b6c erfolgreich beendet durch Worker d828f5f0-5c27-4fed-a058-09c2d046ac41
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 6d260c95-1e35-4fa6-b9a3-fd0be61d4d1c
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 6d260c95-1e35-4fa6-b9a3-fd0be61d4d1c
Job 6d260c95-1e35-4fa6-b9a3-fd0be61d4d1c erfolgreich beendet durch Worker e8358c35-a31f-4045-b640-d1aa137aa9ec
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job e2fd6c92-2c42-4768-9524-77f5003d19af
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job e2fd6c92-2c42-4768-9524-77f5003d19af
Job e2fd6c92-2c42-4768-9524-77f5003d19af erfolgreich beendet durch Worker 29d25095-9d9d-4909-b089-d10dcba3b25b
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job a697e0f5-698c-4d55-bfaa-27e8b2ddfc6b
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job a697e0f5-698c-4d55-bfaa-27e8b2ddfc6b
Job a697e0f5-698c-4d55-bfaa-27e8b2ddfc6b erfolgreich beendet durch Worker b655d9e4-c248-4d85-9bc2-2fc8f5290ee2
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job f31a59e7-510b-4039-838d-adccc94ffba4
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job f31a59e7-510b-4039-838d-adccc94ffba4
Job f31a59e7-510b-4039-838d-adccc94ffba4 erfolgreich beendet durch Worker 38ef6f5a-5d8c-481a-b4ad-0efd59c59592
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 66c9c469-82cb-4490-9349-2bd2a5d03b23
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 66c9c469-82cb-4490-9349-2bd2a5d03b23
Job 66c9c469-82cb-4490-9349-2bd2a5d03b23 erfolgreich beendet durch Worker f60c86af-c993-452c-adbc-270f7312141f
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1b3805be-b1a2-491c-8162-64b78d953d49
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 1b3805be-b1a2-491c-8162-64b78d953d49
Job 1b3805be-b1a2-491c-8162-64b78d953d49 erfolgreich beendet durch Worker 6c00fc9c-b0dc-4637-ac15-1e30e4498b50
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 630e5a09-34d1-4364-93ce-fb40c2680797
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 630e5a09-34d1-4364-93ce-fb40c2680797
Job 630e5a09-34d1-4364-93ce-fb40c2680797 erfolgreich beendet durch Worker d017c421-5f89-486b-86f3-34e730a1356d
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7a90ba2c-38f0-43e6-b45f-4f78b37ce918
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: f802b346-0fec-461a-8cbe-f44123e34e36 Worker: b7a35a8c-9a21-4400-92c6-e687d6e2dd2e
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 7a90ba2c-38f0-43e6-b45f-4f78b37ce918
Job 7a90ba2c-38f0-43e6-b45f-4f78b37ce918 erfolgreich beendet durch Worker f802b346-0fec-461a-8cbe-f44123e34e36
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c3fb337d-08d3-4ef5-b677-4357798c9002
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job c3fb337d-08d3-4ef5-b677-4357798c9002
Job c3fb337d-08d3-4ef5-b677-4357798c9002 erfolgreich beendet durch Worker 944bc8a8-7d40-4955-b05a-59d3e6adeb9d
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 272521b3-5f95-4fdf-95f1-5314e27ef039
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: d7c0113a-9a12-410c-a8f0-f1ce888cb33b Worker: 8b6cde73-cb59-4acd-ae37-95d979c31d4a
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 272521b3-5f95-4fdf-95f1-5314e27ef039
Job 272521b3-5f95-4fdf-95f1-5314e27ef039 erfolgreich beendet durch Worker d7c0113a-9a12-410c-a8f0-f1ce888cb33b
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job e020f303-fd00-4418-83c0-64120dec5a1e
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 63e82a0f-1c1a-44e4-a4e8-3f0e3e9446c2 Worker: 1bfe768d-41d6-4023-a281-144e67604d87
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job e020f303-fd00-4418-83c0-64120dec5a1e
Job e020f303-fd00-4418-83c0-64120dec5a1e erfolgreich beendet durch Worker 63e82a0f-1c1a-44e4-a4e8-3f0e3e9446c2
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 16f17864-664c-4ea3-b27e-0912e8245a78
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 16f17864-664c-4ea3-b27e-0912e8245a78
Job 16f17864-664c-4ea3-b27e-0912e8245a78 erfolgreich beendet durch Worker b12bb339-08c0-48fd-9e0c-067fb6d8c6ff
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 351032b3-9049-43f7-b8a2-1624a2ca87b7
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: ff4e4797-2c34-4a63-b8e6-76a247e022c3 Worker: e9d9456b-bcf9-4e1f-9867-badb6d2382ef
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 351032b3-9049-43f7-b8a2-1624a2ca87b7
Job 351032b3-9049-43f7-b8a2-1624a2ca87b7 erfolgreich beendet durch Worker ff4e4797-2c34-4a63-b8e6-76a247e022c3
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 58458ff0-9f9c-4fab-8597-574b262905df
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 56f3635e-3cad-42c6-b407-6f4cc3a87e54 Worker: f6b780b9-5612-44a4-9afd-22aa0c9f0f78
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 58458ff0-9f9c-4fab-8597-574b262905df
Job 58458ff0-9f9c-4fab-8597-574b262905df erfolgreich beendet durch Worker 56f3635e-3cad-42c6-b407-6f4cc3a87e54
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c3248c6a-03fa-48f1-8afb-fdf2d5520b4c
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job c3248c6a-03fa-48f1-8afb-fdf2d5520b4c
Job c3248c6a-03fa-48f1-8afb-fdf2d5520b4c erfolgreich beendet durch Worker b1fbe337-501b-4b52-82dd-762c4ccd0662
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job a1122608-ab7e-40f6-9520-f373b4e8bbc2
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: ff1c19e9-6cf4-4381-b972-956cd6d635f9 Worker: a8c8fec0-7488-428f-b339-ca8c8767c296
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job a1122608-ab7e-40f6-9520-f373b4e8bbc2
Job a1122608-ab7e-40f6-9520-f373b4e8bbc2 erfolgreich beendet durch Worker ff1c19e9-6cf4-4381-b972-956cd6d635f9
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 062a84d1-82ad-4049-a1e2-f18cf263a3b7
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: bee46da9-ecfb-4529-9788-d27192f25744 Worker: 4ae63b7f-055c-415e-94b6-efa111676d68
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 062a84d1-82ad-4049-a1e2-f18cf263a3b7
Job 062a84d1-82ad-4049-a1e2-f18cf263a3b7 erfolgreich beendet durch Worker bee46da9-ecfb-4529-9788-d27192f25744
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 69e19f7c-641a-443e-8c50-fa02c709a4ad
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: ddc09a34-39c7-496f-9c59-fe9024d82528 Worker: 244c0625-beac-4930-9b25-59da0fa61778
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 69e19f7c-641a-443e-8c50-fa02c709a4ad
Job 69e19f7c-641a-443e-8c50-fa02c709a4ad erfolgreich beendet durch Worker ddc09a34-39c7-496f-9c59-fe9024d82528
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 3362feb2-1d0a-4e1d-ba69-4b0e867cc1e7
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: fef33f99-e1b0-4171-9f99-ab2a9124b446 Worker: 0c081aa8-2f3b-469d-a661-bed5c8ee0f59
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 3362feb2-1d0a-4e1d-ba69-4b0e867cc1e7
Job 3362feb2-1d0a-4e1d-ba69-4b0e867cc1e7 erfolgreich beendet durch Worker fef33f99-e1b0-4171-9f99-ab2a9124b446
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c4f21919-6296-425b-90df-109f40f2c2b5
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job c4f21919-6296-425b-90df-109f40f2c2b5
Job c4f21919-6296-425b-90df-109f40f2c2b5 erfolgreich beendet durch Worker 674d7af4-9507-4e00-b417-3f8884b35f53
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job a20ad435-5c71-41e7-9744-a6c06f92b655
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job a20ad435-5c71-41e7-9744-a6c06f92b655
Job a20ad435-5c71-41e7-9744-a6c06f92b655 erfolgreich beendet durch Worker a0def4c2-fa64-401e-8a0c-c4791ae531da
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job e4b4df6e-45b7-4610-b18c-1ab24096076b
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 32819072-1d94-4f70-a517-8cdf9ae5d254 Worker: d1c97325-c039-473b-bc73-249fbc0ab96e
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job e4b4df6e-45b7-4610-b18c-1ab24096076b
Job e4b4df6e-45b7-4610-b18c-1ab24096076b erfolgreich beendet durch Worker 32819072-1d94-4f70-a517-8cdf9ae5d254
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 565c4b9a-4991-4230-91ce-079d47a91fa5
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 565c4b9a-4991-4230-91ce-079d47a91fa5
Job 565c4b9a-4991-4230-91ce-079d47a91fa5 erfolgreich beendet durch Worker 95c92855-edc1-4ed9-a844-b0370d7822fe
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job e85cae3e-5f85-4c7e-94aa-5cc8b654c581
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 78cc0d50-8bf7-497b-ada1-c0ff845e5826 Worker: 7a8c86d5-6487-46e0-b0de-dcc9084e64fb
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job e85cae3e-5f85-4c7e-94aa-5cc8b654c581
Job e85cae3e-5f85-4c7e-94aa-5cc8b654c581 erfolgreich beendet durch Worker 78cc0d50-8bf7-497b-ada1-c0ff845e5826
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 03011a43-a91b-4d0f-8c30-d8be0d4017cd
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 6a34d3ce-519c-407d-9b9c-122270996b97 Worker: 8e0d89ec-222c-4e14-8416-e9c23f648886
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 03011a43-a91b-4d0f-8c30-d8be0d4017cd
Job 03011a43-a91b-4d0f-8c30-d8be0d4017cd erfolgreich beendet durch Worker 6a34d3ce-519c-407d-9b9c-122270996b97
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 6d082867-9994-4b24-a056-0b540934e454
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 6d082867-9994-4b24-a056-0b540934e454
Job 6d082867-9994-4b24-a056-0b540934e454 erfolgreich beendet durch Worker fb264bfa-3945-49c3-bc54-ce1aa59355b1
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 8c8082ca-bd3b-4db3-a798-7b1a631d643b
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: f67a39d9-8e2f-4f77-9e90-3ac91bc570ed Worker: 7a601d88-24c3-4a96-a02c-ca5e4d90fb0d
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 8c8082ca-bd3b-4db3-a798-7b1a631d643b
Job 8c8082ca-bd3b-4db3-a798-7b1a631d643b erfolgreich beendet durch Worker f67a39d9-8e2f-4f77-9e90-3ac91bc570ed
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job dc212dce-12f1-4b65-967d-b578ff9bbff4
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job dc212dce-12f1-4b65-967d-b578ff9bbff4
Job dc212dce-12f1-4b65-967d-b578ff9bbff4 erfolgreich beendet durch Worker e67894c2-4ced-4f31-b39f-cb870fdc793c
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1a7e25da-aea8-45d2-b2e8-d881f37ae669
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 1f052cec-7628-476f-b951-5fa68ccb1ba8 Worker: 9fcfbc1a-d9d9-4e9a-8ae8-d08ac4925e08
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 1a7e25da-aea8-45d2-b2e8-d881f37ae669
Job 1a7e25da-aea8-45d2-b2e8-d881f37ae669 erfolgreich beendet durch Worker 1f052cec-7628-476f-b951-5fa68ccb1ba8
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 0f64f7fb-2736-4a79-90ea-61f9c7c292f2
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 219002c6-fd18-4789-8b0d-c20ba0e75743 Worker: 816d71f3-e467-446b-94f8-57a1ecb799f8
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 0f64f7fb-2736-4a79-90ea-61f9c7c292f2
Job 0f64f7fb-2736-4a79-90ea-61f9c7c292f2 erfolgreich beendet durch Worker 219002c6-fd18-4789-8b0d-c20ba0e75743
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job ee2e5904-0e1f-4d53-9944-656ad77a99ae
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 3c9d6611-3bc6-43b8-ab17-4bd5fa5171ca Worker: 1335289a-6ebc-4ee7-9a92-12dbd075e33d
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job ee2e5904-0e1f-4d53-9944-656ad77a99ae
Job ee2e5904-0e1f-4d53-9944-656ad77a99ae erfolgreich beendet durch Worker 3c9d6611-3bc6-43b8-ab17-4bd5fa5171ca
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 6f859d10-e62c-4d2a-8d9f-6ea1dec5462b
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 6322a59b-63b8-42ce-b012-9884d17b1701 Worker: 4d149c19-e97c-4bfa-8579-59346d59dab5
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 6f859d10-e62c-4d2a-8d9f-6ea1dec5462b
Job 6f859d10-e62c-4d2a-8d9f-6ea1dec5462b erfolgreich beendet durch Worker 6322a59b-63b8-42ce-b012-9884d17b1701
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job bae0cad4-398d-4e9f-94d1-204e8197858d
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job bae0cad4-398d-4e9f-94d1-204e8197858d
Job bae0cad4-398d-4e9f-94d1-204e8197858d erfolgreich beendet durch Worker 5120bf1f-c418-4d8f-96bf-12a67567a0db
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7c8217e5-0e20-47b6-8e0c-551ca65589cf
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 7c8217e5-0e20-47b6-8e0c-551ca65589cf
Job 7c8217e5-0e20-47b6-8e0c-551ca65589cf erfolgreich beendet durch Worker 3d275ce9-f749-4360-a187-47c9fff5d372
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 5de5ed91-0b09-4c03-ad5a-8e4268c249cd
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 059d76b5-3f7b-4e4f-b69e-26428019fe0d Worker: 1a0d8d98-3ea3-4c2f-9b4c-bdf86bc9c1e1
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 5de5ed91-0b09-4c03-ad5a-8e4268c249cd
Job 5de5ed91-0b09-4c03-ad5a-8e4268c249cd erfolgreich beendet durch Worker 059d76b5-3f7b-4e4f-b69e-26428019fe0d
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7f5dc5f4-622e-470f-af37-c9f00a53dc90
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 7f5dc5f4-622e-470f-af37-c9f00a53dc90
Job 7f5dc5f4-622e-470f-af37-c9f00a53dc90 erfolgreich beendet durch Worker ae4d7d62-c35f-4984-b1d3-cdb350463e17
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job dd1d576d-706c-4ecc-bc56-f8631ac170ec
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job dd1d576d-706c-4ecc-bc56-f8631ac170ec
Job dd1d576d-706c-4ecc-bc56-f8631ac170ec erfolgreich beendet durch Worker ba0f5b0e-eff5-4ad3-a3fd-fb7ed3faa571
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d41825c6-26a5-4f8a-8171-6dbba2102694
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job d41825c6-26a5-4f8a-8171-6dbba2102694
Job d41825c6-26a5-4f8a-8171-6dbba2102694 erfolgreich beendet durch Worker bccf15f3-492a-47b0-a517-0884ceb5e257
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 301581f8-7b91-43f4-888d-cfe8c0d53224
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 301581f8-7b91-43f4-888d-cfe8c0d53224
Job 301581f8-7b91-43f4-888d-cfe8c0d53224 erfolgreich beendet durch Worker 6e36eca0-c808-4473-9b8b-c80f87e24663
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 90d9a5c6-877a-4c81-b5ab-6df0327f9883
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 90d9a5c6-877a-4c81-b5ab-6df0327f9883
Job 90d9a5c6-877a-4c81-b5ab-6df0327f9883 erfolgreich beendet durch Worker 5b887c4c-4280-4ed1-a10a-0a18e457466a
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 737e2020-fa5a-4a4c-be0c-73e9aba0bd15
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: 60244537-9c75-4b99-960b-5792a5f0b5c2 Worker: fb90ebec-8060-4d14-81b7-c7bc603a9807
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 737e2020-fa5a-4a4c-be0c-73e9aba0bd15
Job 737e2020-fa5a-4a4c-be0c-73e9aba0bd15 erfolgreich beendet durch Worker 60244537-9c75-4b99-960b-5792a5f0b5c2
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c230b501-f15e-4f1e-ade9-1eba4a195511
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job c230b501-f15e-4f1e-ade9-1eba4a195511
Job c230b501-f15e-4f1e-ade9-1eba4a195511 erfolgreich beendet durch Worker d686fa0f-1405-4768-8501-8c151a6f7f76
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
```

## WorkerCrashRecoveryTest.atomicSaveTest() (Z-4)
```log
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1470f275-f3c0-4570-ba72-2a7d23c9c311
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 1470f275-f3c0-4570-ba72-2a7d23c9c311
Testhook hat Fehler geworfen
Job:
Idempotenz-Key: 1470f275-f3c0-4570-ba72-2a7d23c9c311
Status: RUNNING
Ergebnis: null
```

## JobStatusModellTest.finishJobAndRecoveryRaceNeverReversesTerminalState() (Z-5)
```log
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job b92c9403-0075-4779-8532-922a5021bef2
Job: Zustandsübergang von RUNNING nach FAILED für Job b92c9403-0075-4779-8532-922a5021bef2
Recovery: Job b92c9403-0075-4779-8532-922a5021bef2 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 0df60694-2457-409a-a76a-d6cad620809c
Job: Zustandsübergang von RUNNING nach FAILED für Job 0df60694-2457-409a-a76a-d6cad620809c
Recovery: Job 0df60694-2457-409a-a76a-d6cad620809c wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job db5b6c95-2d74-4dea-9c84-798f028f942d
Job: Zustandsübergang von RUNNING nach FAILED für Job db5b6c95-2d74-4dea-9c84-798f028f942d
Recovery: Job db5b6c95-2d74-4dea-9c84-798f028f942d wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 66580ada-8432-411c-9e0d-9819e188fe9a
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 66580ada-8432-411c-9e0d-9819e188fe9a
Job 66580ada-8432-411c-9e0d-9819e188fe9a erfolgreich beendet durch Worker 986f69b8-ae90-49c6-94b3-63495395ed8a
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job b812e21d-9764-4386-a879-f041ab563ce8
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job b812e21d-9764-4386-a879-f041ab563ce8
Job b812e21d-9764-4386-a879-f041ab563ce8 erfolgreich beendet durch Worker b75d5e5b-c8a8-46d8-8352-20455b11f430
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2681f683-c332-40d8-b622-13112ff9d3d9
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 2681f683-c332-40d8-b622-13112ff9d3d9
Job 2681f683-c332-40d8-b622-13112ff9d3d9 erfolgreich beendet durch Worker 55d8cfa7-c2ba-443c-b21a-473eef5dd85a
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7a9f4fcd-cc77-4651-b00e-bf58041b71d1
Job: Zustandsübergang von RUNNING nach FAILED für Job 7a9f4fcd-cc77-4651-b00e-bf58041b71d1
Recovery: Job 7a9f4fcd-cc77-4651-b00e-bf58041b71d1 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job e2700612-e852-4a48-91fc-96448ce84f92
Job: Zustandsübergang von RUNNING nach FAILED für Job e2700612-e852-4a48-91fc-96448ce84f92
Recovery: Job e2700612-e852-4a48-91fc-96448ce84f92 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1ba2d4ce-d800-463c-bee0-758e9081cd6c
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 1ba2d4ce-d800-463c-bee0-758e9081cd6c
Job 1ba2d4ce-d800-463c-bee0-758e9081cd6c erfolgreich beendet durch Worker 32058a54-fc90-44a0-98e0-00a07fa03867
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 3e09dbc3-87e1-4716-9371-8de03e8d6338
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 3e09dbc3-87e1-4716-9371-8de03e8d6338
Job 3e09dbc3-87e1-4716-9371-8de03e8d6338 erfolgreich beendet durch Worker ccf8e82c-cc1a-4460-ba7e-d59106a1c110
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job bafd0f1c-ddbc-4770-8f31-3779fe019a3d
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job bafd0f1c-ddbc-4770-8f31-3779fe019a3d
Job bafd0f1c-ddbc-4770-8f31-3779fe019a3d erfolgreich beendet durch Worker decf1f2e-4f07-4ff6-a4d3-bcbb5c77ae8f
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 637b412e-0916-4cad-a185-abb7c56dc44a
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 637b412e-0916-4cad-a185-abb7c56dc44a
Job 637b412e-0916-4cad-a185-abb7c56dc44a erfolgreich beendet durch Worker a49fac41-ed51-415e-a358-519554c61811
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 02a9b163-54c8-4f0a-a675-e06c9b135c68
Job: Zustandsübergang von RUNNING nach FAILED für Job 02a9b163-54c8-4f0a-a675-e06c9b135c68
Recovery: Job 02a9b163-54c8-4f0a-a675-e06c9b135c68 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d77b60b8-cf0f-4c29-90be-071c5650f584
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job d77b60b8-cf0f-4c29-90be-071c5650f584
Job d77b60b8-cf0f-4c29-90be-071c5650f584 erfolgreich beendet durch Worker e4a97599-3e8a-4f4b-9f49-dffe67d36489
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 03b66e25-9ab7-4305-accc-bda0cdbb85cb
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 03b66e25-9ab7-4305-accc-bda0cdbb85cb
Job 03b66e25-9ab7-4305-accc-bda0cdbb85cb erfolgreich beendet durch Worker 6bb9b8ef-a18c-4dbd-8db3-1f44fb77aa81
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 3d559080-fc0d-43a5-a85a-4b58ea2e05fa
Job: Zustandsübergang von RUNNING nach FAILED für Job 3d559080-fc0d-43a5-a85a-4b58ea2e05fa
Recovery: Job 3d559080-fc0d-43a5-a85a-4b58ea2e05fa wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 0b646c6f-374f-4572-890d-fa51144573be
Job: Zustandsübergang von RUNNING nach FAILED für Job 0b646c6f-374f-4572-890d-fa51144573be
Recovery: Job 0b646c6f-374f-4572-890d-fa51144573be wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 43326552-b057-4bda-bacd-620ff370f8cc
Job: Zustandsübergang von RUNNING nach FAILED für Job 43326552-b057-4bda-bacd-620ff370f8cc
Recovery: Job 43326552-b057-4bda-bacd-620ff370f8cc wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 61b169ff-4faa-4b12-a5d6-59ae5b97d00e
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 61b169ff-4faa-4b12-a5d6-59ae5b97d00e
Job 61b169ff-4faa-4b12-a5d6-59ae5b97d00e erfolgreich beendet durch Worker 3b140e4d-93e2-4b2e-8c6d-b90740e8a2ca
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c188bf0c-1b5b-4098-9c10-70d224148401
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job c188bf0c-1b5b-4098-9c10-70d224148401
Job c188bf0c-1b5b-4098-9c10-70d224148401 erfolgreich beendet durch Worker fa457686-c9c6-4808-8c58-3e59c46f0a83
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9b2c4a8c-3694-4319-9478-f7edf89ecbda
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 9b2c4a8c-3694-4319-9478-f7edf89ecbda
Job 9b2c4a8c-3694-4319-9478-f7edf89ecbda erfolgreich beendet durch Worker 32be936d-39d4-403d-9fbe-ed8241d8ea23
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9beb94d5-5011-474f-bded-f42b607dc5c0
Job: Zustandsübergang von RUNNING nach FAILED für Job 9beb94d5-5011-474f-bded-f42b607dc5c0
Recovery: Job 9beb94d5-5011-474f-bded-f42b607dc5c0 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 0bf09b30-ab64-470a-922b-3ee0fd19978d
Job: Zustandsübergang von RUNNING nach FAILED für Job 0bf09b30-ab64-470a-922b-3ee0fd19978d
Recovery: Job 0bf09b30-ab64-470a-922b-3ee0fd19978d wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9abfd6fa-e54d-4f28-9752-d2f344e7c649
Job: Zustandsübergang von RUNNING nach FAILED für Job 9abfd6fa-e54d-4f28-9752-d2f344e7c649
Recovery: Job 9abfd6fa-e54d-4f28-9752-d2f344e7c649 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 3d39cbf1-d0fa-45c5-bf91-fd404298d649
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 3d39cbf1-d0fa-45c5-bf91-fd404298d649
Job 3d39cbf1-d0fa-45c5-bf91-fd404298d649 erfolgreich beendet durch Worker 064a590c-2f06-43e6-980d-025bd0eb8a8a
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1241f343-c1b6-4e96-bbb2-1601d20b3b24
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 1241f343-c1b6-4e96-bbb2-1601d20b3b24
Job 1241f343-c1b6-4e96-bbb2-1601d20b3b24 erfolgreich beendet durch Worker 5a3e03df-4332-4a3b-b141-90984b9bba28
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 5eedaf3d-53b9-446f-b0a1-dcfe8f254016
Job: Zustandsübergang von RUNNING nach FAILED für Job 5eedaf3d-53b9-446f-b0a1-dcfe8f254016
Recovery: Job 5eedaf3d-53b9-446f-b0a1-dcfe8f254016 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c452cc69-ddf3-4d7f-9e10-9f4782e13bd5
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job c452cc69-ddf3-4d7f-9e10-9f4782e13bd5
Job c452cc69-ddf3-4d7f-9e10-9f4782e13bd5 erfolgreich beendet durch Worker b464a475-07c6-4877-a37e-d4f11d24bb35
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d1171f2c-5939-413c-a1a6-530bdcd06238
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job d1171f2c-5939-413c-a1a6-530bdcd06238
Job d1171f2c-5939-413c-a1a6-530bdcd06238 erfolgreich beendet durch Worker 3df07ea9-9a3d-4855-9792-df7b6a6c2840
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9d2d4543-6e5b-4cb8-8e60-a57a67e19de8
Job: Zustandsübergang von RUNNING nach FAILED für Job 9d2d4543-6e5b-4cb8-8e60-a57a67e19de8
Recovery: Job 9d2d4543-6e5b-4cb8-8e60-a57a67e19de8 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7537a73f-536e-41ac-930f-93bc960c085a
Job: Zustandsübergang von RUNNING nach FAILED für Job 7537a73f-536e-41ac-930f-93bc960c085a
Recovery: Job 7537a73f-536e-41ac-930f-93bc960c085a wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job adb30c1f-ab18-45aa-84a5-38ea30edc38d
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job adb30c1f-ab18-45aa-84a5-38ea30edc38d
Job adb30c1f-ab18-45aa-84a5-38ea30edc38d erfolgreich beendet durch Worker 370e1df9-a911-4838-b938-f7494966fe59
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job bb3db3d3-1d76-47b5-a814-fc4272c5e9a1
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job bb3db3d3-1d76-47b5-a814-fc4272c5e9a1
Job bb3db3d3-1d76-47b5-a814-fc4272c5e9a1 erfolgreich beendet durch Worker aead9052-eab2-4e45-893c-d5e4bb0b3dd1
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 113c3c7a-6662-415e-9d42-c82b8d49eb89
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 113c3c7a-6662-415e-9d42-c82b8d49eb89
Job 113c3c7a-6662-415e-9d42-c82b8d49eb89 erfolgreich beendet durch Worker 246f45cb-7797-4bba-961e-95cb6ea0322e
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job dc423199-a3b8-4d12-ae94-cd4ba42a85ca
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job dc423199-a3b8-4d12-ae94-cd4ba42a85ca
Job dc423199-a3b8-4d12-ae94-cd4ba42a85ca erfolgreich beendet durch Worker 2fd8c195-8a37-484f-9351-e654ed141350
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c58a369a-9d91-46f5-a1d8-6916f2d257d8
Job: Zustandsübergang von RUNNING nach FAILED für Job c58a369a-9d91-46f5-a1d8-6916f2d257d8
Recovery: Job c58a369a-9d91-46f5-a1d8-6916f2d257d8 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job aa5a729c-7119-4b85-9a05-ff65cfb67db1
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job aa5a729c-7119-4b85-9a05-ff65cfb67db1
Job aa5a729c-7119-4b85-9a05-ff65cfb67db1 erfolgreich beendet durch Worker 0f80d7f7-2dff-4c50-9ab8-eb484e638bae
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 68790e32-1a96-4939-971f-21453e6abe85
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 68790e32-1a96-4939-971f-21453e6abe85
Job 68790e32-1a96-4939-971f-21453e6abe85 erfolgreich beendet durch Worker 64a6fcc6-3aeb-4391-9c64-30d30b115b22
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 60025692-53d4-4631-9ea5-0f57a2471c44
Job: Zustandsübergang von RUNNING nach FAILED für Job 60025692-53d4-4631-9ea5-0f57a2471c44
Recovery: Job 60025692-53d4-4631-9ea5-0f57a2471c44 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job ca609218-ef62-4b9a-bb24-eb69994525e6
Job: Zustandsübergang von RUNNING nach FAILED für Job ca609218-ef62-4b9a-bb24-eb69994525e6
Recovery: Job ca609218-ef62-4b9a-bb24-eb69994525e6 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job acd2be46-fed0-4f27-bb26-b23aa0c74570
Job: Zustandsübergang von RUNNING nach FAILED für Job acd2be46-fed0-4f27-bb26-b23aa0c74570
Recovery: Job acd2be46-fed0-4f27-bb26-b23aa0c74570 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 3cc0ca72-b6b4-4307-b295-64f4a635f2ac
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 3cc0ca72-b6b4-4307-b295-64f4a635f2ac
Job 3cc0ca72-b6b4-4307-b295-64f4a635f2ac erfolgreich beendet durch Worker cad2aef5-26e8-4464-8bd2-983bdb84b70f
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d3b88441-f4c5-4ae4-893e-1e5aeb53d77e
Job: Zustandsübergang von RUNNING nach FAILED für Job d3b88441-f4c5-4ae4-893e-1e5aeb53d77e
Recovery: Job d3b88441-f4c5-4ae4-893e-1e5aeb53d77e wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7fe7b131-90fd-4de0-8a6a-5b7e3e5fa0a4
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 7fe7b131-90fd-4de0-8a6a-5b7e3e5fa0a4
Job 7fe7b131-90fd-4de0-8a6a-5b7e3e5fa0a4 erfolgreich beendet durch Worker 2a74d74c-421a-457a-a66e-8b68834bd9a1
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 00fe9071-9c23-4c3a-b9ba-0abba5750ae3
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 00fe9071-9c23-4c3a-b9ba-0abba5750ae3
Job 00fe9071-9c23-4c3a-b9ba-0abba5750ae3 erfolgreich beendet durch Worker bf583018-0308-48a7-822d-bbaa46e8fb03
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job fd2cb41f-a54d-4f84-ae78-8e8e4f1003e0
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job fd2cb41f-a54d-4f84-ae78-8e8e4f1003e0
Job fd2cb41f-a54d-4f84-ae78-8e8e4f1003e0 erfolgreich beendet durch Worker 1331b756-0d81-460f-93b7-d96179cdae4d
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job cac8bc33-22b3-4915-ad77-118738fea35b
Job: Zustandsübergang von RUNNING nach FAILED für Job cac8bc33-22b3-4915-ad77-118738fea35b
Recovery: Job cac8bc33-22b3-4915-ad77-118738fea35b wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c31c9025-aad4-473e-a2b9-a50e6719de52
Job: Zustandsübergang von RUNNING nach FAILED für Job c31c9025-aad4-473e-a2b9-a50e6719de52
Recovery: Job c31c9025-aad4-473e-a2b9-a50e6719de52 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 19fa2887-ff7a-411a-83d3-71a362a15c75
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 19fa2887-ff7a-411a-83d3-71a362a15c75
Job 19fa2887-ff7a-411a-83d3-71a362a15c75 erfolgreich beendet durch Worker b472573f-697f-4b42-a6be-29fc109a289d
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d214f037-7aa8-4630-b632-0ccf00e5ed1d
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job d214f037-7aa8-4630-b632-0ccf00e5ed1d
Job d214f037-7aa8-4630-b632-0ccf00e5ed1d erfolgreich beendet durch Worker efd0f502-bf6e-49d3-b664-748eeb6b1c59
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 5d5d6954-8118-40a1-bdef-01334a8549b6
Job: Zustandsübergang von RUNNING nach FAILED für Job 5d5d6954-8118-40a1-bdef-01334a8549b6
Recovery: Job 5d5d6954-8118-40a1-bdef-01334a8549b6 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job f35b02a8-8110-4cef-a8cf-f17c2724464f
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job f35b02a8-8110-4cef-a8cf-f17c2724464f
Job f35b02a8-8110-4cef-a8cf-f17c2724464f erfolgreich beendet durch Worker 9964f175-22aa-4237-a198-cbf4d57204a1
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2c2b97fe-7718-4c5c-a000-54e89a014996
Job: Zustandsübergang von RUNNING nach FAILED für Job 2c2b97fe-7718-4c5c-a000-54e89a014996
Recovery: Job 2c2b97fe-7718-4c5c-a000-54e89a014996 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 67cf1283-c947-486d-bd23-99b2ced93b8f
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 67cf1283-c947-486d-bd23-99b2ced93b8f
Job 67cf1283-c947-486d-bd23-99b2ced93b8f erfolgreich beendet durch Worker 74a2d634-f6ea-43f5-87f4-afeab5795cbc
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 080d181d-4ce9-4f27-8134-93e7c1d010eb
Job: Zustandsübergang von RUNNING nach FAILED für Job 080d181d-4ce9-4f27-8134-93e7c1d010eb
Recovery: Job 080d181d-4ce9-4f27-8134-93e7c1d010eb wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 54fd6afe-2da7-4523-a88d-d96359384cab
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 54fd6afe-2da7-4523-a88d-d96359384cab
Job 54fd6afe-2da7-4523-a88d-d96359384cab erfolgreich beendet durch Worker 2a97f96b-5854-4051-9a2b-00e42a150b34
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 203d8c8b-59ca-479b-bbd4-cf3a0d1798bb
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 203d8c8b-59ca-479b-bbd4-cf3a0d1798bb
Job 203d8c8b-59ca-479b-bbd4-cf3a0d1798bb erfolgreich beendet durch Worker ebe09095-693c-4c30-b315-7d142b118cb2
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 0cfd3558-f704-44aa-ae52-2be9faf7d016
Job: Zustandsübergang von RUNNING nach FAILED für Job 0cfd3558-f704-44aa-ae52-2be9faf7d016
Recovery: Job 0cfd3558-f704-44aa-ae52-2be9faf7d016 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 8b8b8b7d-0d54-4b06-a359-707c9ceeb94b
Job: Zustandsübergang von RUNNING nach FAILED für Job 8b8b8b7d-0d54-4b06-a359-707c9ceeb94b
Recovery: Job 8b8b8b7d-0d54-4b06-a359-707c9ceeb94b wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 8936461d-7de2-414a-bd6d-55586f9a628c
Job: Zustandsübergang von RUNNING nach FAILED für Job 8936461d-7de2-414a-bd6d-55586f9a628c
Recovery: Job 8936461d-7de2-414a-bd6d-55586f9a628c wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 4f1e41e0-e9d1-4aa2-b6d1-83c8d3e2a948
Job: Zustandsübergang von RUNNING nach FAILED für Job 4f1e41e0-e9d1-4aa2-b6d1-83c8d3e2a948
Recovery: Job 4f1e41e0-e9d1-4aa2-b6d1-83c8d3e2a948 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 45d3ba78-fa08-42ed-8d0d-27640525e64d
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 45d3ba78-fa08-42ed-8d0d-27640525e64d
Job 45d3ba78-fa08-42ed-8d0d-27640525e64d erfolgreich beendet durch Worker fb639539-1ac4-4e8f-9d40-f9d8fd6064ac
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job af8bd683-edc9-4e12-8b68-3b4c29c79e51
Job: Zustandsübergang von RUNNING nach FAILED für Job af8bd683-edc9-4e12-8b68-3b4c29c79e51
Recovery: Job af8bd683-edc9-4e12-8b68-3b4c29c79e51 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 25d2d40e-368d-4f61-9294-741cdc6a5c9c
Job: Zustandsübergang von RUNNING nach FAILED für Job 25d2d40e-368d-4f61-9294-741cdc6a5c9c
Recovery: Job 25d2d40e-368d-4f61-9294-741cdc6a5c9c wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 39a52066-c93b-4854-b9bd-4d95fb56eaa9
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 39a52066-c93b-4854-b9bd-4d95fb56eaa9
Job 39a52066-c93b-4854-b9bd-4d95fb56eaa9 erfolgreich beendet durch Worker 62ab4e07-8f3f-4ecc-95d4-1b9aca218f09
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d014ff66-2591-4d2b-981e-28ebf20542a7
Job: Zustandsübergang von RUNNING nach FAILED für Job d014ff66-2591-4d2b-981e-28ebf20542a7
Recovery: Job d014ff66-2591-4d2b-981e-28ebf20542a7 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 95aa0eb9-1981-490a-8d15-39a9e0005433
Job: Zustandsübergang von RUNNING nach FAILED für Job 95aa0eb9-1981-490a-8d15-39a9e0005433
Recovery: Job 95aa0eb9-1981-490a-8d15-39a9e0005433 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job de2a1a54-5521-4313-9718-e58bd5135e88
Job: Zustandsübergang von RUNNING nach FAILED für Job de2a1a54-5521-4313-9718-e58bd5135e88
Recovery: Job de2a1a54-5521-4313-9718-e58bd5135e88 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 40e67cd9-cf61-400d-a860-3a8564e074fd
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 40e67cd9-cf61-400d-a860-3a8564e074fd
Job 40e67cd9-cf61-400d-a860-3a8564e074fd erfolgreich beendet durch Worker 970cfdff-68a3-45e7-9d5e-99cf4a37724b
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2c8956a4-1ea2-4821-84ee-0dafe8f66194
Job: Zustandsübergang von RUNNING nach FAILED für Job 2c8956a4-1ea2-4821-84ee-0dafe8f66194
Recovery: Job 2c8956a4-1ea2-4821-84ee-0dafe8f66194 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d50178ca-b65d-4cd5-bb51-52c4cd93a7bb
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job d50178ca-b65d-4cd5-bb51-52c4cd93a7bb
Job d50178ca-b65d-4cd5-bb51-52c4cd93a7bb erfolgreich beendet durch Worker 8640d20e-ce9e-49bb-82ad-e286420a5b38
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 4620ec8b-45a4-4d9a-9240-32fed544276c
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 4620ec8b-45a4-4d9a-9240-32fed544276c
Job 4620ec8b-45a4-4d9a-9240-32fed544276c erfolgreich beendet durch Worker 27895431-db1c-4ae0-beb6-6517cc47c676
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 060dfd73-72e3-4064-b895-226f3fc23bb2
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 060dfd73-72e3-4064-b895-226f3fc23bb2
Job 060dfd73-72e3-4064-b895-226f3fc23bb2 erfolgreich beendet durch Worker 07ef54cf-1e6b-4a9e-ad4d-0675fe2b62d3
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 1fa6c7c6-139c-482c-8498-b5c7b68b9937
Job: Zustandsübergang von RUNNING nach FAILED für Job 1fa6c7c6-139c-482c-8498-b5c7b68b9937
Recovery: Job 1fa6c7c6-139c-482c-8498-b5c7b68b9937 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job f20a93e6-bb6a-4f3f-8e88-b951397875d2
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job f20a93e6-bb6a-4f3f-8e88-b951397875d2
Job f20a93e6-bb6a-4f3f-8e88-b951397875d2 erfolgreich beendet durch Worker a951555a-c4e4-4d03-a840-d78e470cb356
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 55231e63-6cf3-4231-aaa9-3bbca81cb29f
Job: Zustandsübergang von RUNNING nach FAILED für Job 55231e63-6cf3-4231-aaa9-3bbca81cb29f
Recovery: Job 55231e63-6cf3-4231-aaa9-3bbca81cb29f wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 9319004a-e3d7-4f57-b008-020361f9311f
Job: Zustandsübergang von RUNNING nach FAILED für Job 9319004a-e3d7-4f57-b008-020361f9311f
Recovery: Job 9319004a-e3d7-4f57-b008-020361f9311f wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job dc961c85-59eb-480c-b3df-be9b8cbabdfa
Job: Zustandsübergang von RUNNING nach FAILED für Job dc961c85-59eb-480c-b3df-be9b8cbabdfa
Recovery: Job dc961c85-59eb-480c-b3df-be9b8cbabdfa wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job d89544ee-50ad-4c54-8d78-fc3ecbbc611c
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job d89544ee-50ad-4c54-8d78-fc3ecbbc611c
Job d89544ee-50ad-4c54-8d78-fc3ecbbc611c erfolgreich beendet durch Worker 8559fe77-c02b-4021-8409-8b79a5e3de19
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job b0122c84-776c-4c2f-8253-ebf9e9c1ad8a
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job b0122c84-776c-4c2f-8253-ebf9e9c1ad8a
Job b0122c84-776c-4c2f-8253-ebf9e9c1ad8a erfolgreich beendet durch Worker 91923c16-6c59-4f01-b4c3-d3f9ab4cb8bc
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 4e4fa003-cffe-44e9-83fc-bcf717ba58d8
Job: Zustandsübergang von RUNNING nach FAILED für Job 4e4fa003-cffe-44e9-83fc-bcf717ba58d8
Recovery: Job 4e4fa003-cffe-44e9-83fc-bcf717ba58d8 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job b1e6d8d9-1678-43e5-ac03-14d56445053b
Job: Zustandsübergang von RUNNING nach FAILED für Job b1e6d8d9-1678-43e5-ac03-14d56445053b
Recovery: Job b1e6d8d9-1678-43e5-ac03-14d56445053b wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job dd8b2918-9a26-41c8-afbb-818d765aeb88
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job dd8b2918-9a26-41c8-afbb-818d765aeb88
Job dd8b2918-9a26-41c8-afbb-818d765aeb88 erfolgreich beendet durch Worker ff53d68d-f07e-4acd-b87d-15141726621a
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job ebbe693d-2b3f-424a-b308-25b9cc23a60c
Job: Zustandsübergang von RUNNING nach FAILED für Job ebbe693d-2b3f-424a-b308-25b9cc23a60c
Recovery: Job ebbe693d-2b3f-424a-b308-25b9cc23a60c wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 6bb2726a-6ddb-48e9-9d8d-8570a5f4e669
Job: Zustandsübergang von RUNNING nach FAILED für Job 6bb2726a-6ddb-48e9-9d8d-8570a5f4e669
Recovery: Job 6bb2726a-6ddb-48e9-9d8d-8570a5f4e669 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job ea5283de-0c87-407c-9115-7d39f3ac6bbc
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job ea5283de-0c87-407c-9115-7d39f3ac6bbc
Job ea5283de-0c87-407c-9115-7d39f3ac6bbc erfolgreich beendet durch Worker 6717cbdd-c7f6-40e2-b858-070ee21c1230
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job ac4435ea-7a2b-42eb-a35c-afcf9c18f86f
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job ac4435ea-7a2b-42eb-a35c-afcf9c18f86f
Job ac4435ea-7a2b-42eb-a35c-afcf9c18f86f erfolgreich beendet durch Worker 152acadd-4881-4c9f-a352-4f277100e748
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job f91bdb3e-b21b-4ff8-b806-d9a91ed67872
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job f91bdb3e-b21b-4ff8-b806-d9a91ed67872
Job f91bdb3e-b21b-4ff8-b806-d9a91ed67872 erfolgreich beendet durch Worker fb18f4c0-a1b9-435d-8101-4672d2c8a714
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job e157600d-e170-4bf3-baf6-577e127e361e
Job: Zustandsübergang von RUNNING nach FAILED für Job e157600d-e170-4bf3-baf6-577e127e361e
Recovery: Job e157600d-e170-4bf3-baf6-577e127e361e wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 12fda751-7a5a-49c3-8a9c-d5f5b7444acd
Job: Zustandsübergang von RUNNING nach FAILED für Job 12fda751-7a5a-49c3-8a9c-d5f5b7444acd
Recovery: Job 12fda751-7a5a-49c3-8a9c-d5f5b7444acd wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c172b753-f035-4552-acd0-5053d52218e5
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job c172b753-f035-4552-acd0-5053d52218e5
Job c172b753-f035-4552-acd0-5053d52218e5 erfolgreich beendet durch Worker ca946ae8-d434-497c-89f5-e98582901314
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 09079c26-a1d8-45c4-b56e-73eb75517efc
Job: Zustandsübergang von RUNNING nach FAILED für Job 09079c26-a1d8-45c4-b56e-73eb75517efc
Recovery: Job 09079c26-a1d8-45c4-b56e-73eb75517efc wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 0b73703d-21ee-4b2f-bef8-496d741e659e
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 0b73703d-21ee-4b2f-bef8-496d741e659e
Job 0b73703d-21ee-4b2f-bef8-496d741e659e erfolgreich beendet durch Worker 8c80cb0d-0fac-488a-ad0d-ea58c24201de
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job dd04c2c4-49e6-4ee2-9af5-7bafc07e5a67
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job dd04c2c4-49e6-4ee2-9af5-7bafc07e5a67
Job dd04c2c4-49e6-4ee2-9af5-7bafc07e5a67 erfolgreich beendet durch Worker fcc91f82-f8aa-43d6-b794-75be6e184d98
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 89b2bf81-5a34-4ea8-8b7d-f30750cc23ad
Job: Zustandsübergang von RUNNING nach FAILED für Job 89b2bf81-5a34-4ea8-8b7d-f30750cc23ad
Recovery: Job 89b2bf81-5a34-4ea8-8b7d-f30750cc23ad wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job 10cffde8-1713-431e-bdb4-035e8b607303
Job: Zustandsübergang von RUNNING nach FAILED für Job 10cffde8-1713-431e-bdb4-035e8b607303
Recovery: Job 10cffde8-1713-431e-bdb4-035e8b607303 wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c72aa32b-201d-4b16-bf67-30a9c023fb6c
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job c72aa32b-201d-4b16-bf67-30a9c023fb6c
Job c72aa32b-201d-4b16-bf67-30a9c023fb6c erfolgreich beendet durch Worker 8af6a0c2-97eb-4c96-b4fc-48c31b1ebb63
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c8cfe6a1-53e7-4b22-9a1b-0a682887418e
Job: Zustandsübergang von RUNNING nach FAILED für Job c8cfe6a1-53e7-4b22-9a1b-0a682887418e
Recovery: Job c8cfe6a1-53e7-4b22-9a1b-0a682887418e wird aufgrund zu vieler Versuche als FAILED markiert
WorkerService: Job hat nicht den Status RUNNING sondern FAILED, obwohl Worker Ergebnis speichern wollte
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job c9d9a81a-ab34-434b-9892-00dfc98e330c
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job c9d9a81a-ab34-434b-9892-00dfc98e330c
Job c9d9a81a-ab34-434b-9892-00dfc98e330c erfolgreich beendet durch Worker b73491fb-9fdf-4621-97ba-6d489158ae43
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
---------------------------------------Testdurchlauf---------------------------------------
Job: Zustandsübergang von QUEUED nach RUNNING für Job cd720625-0c37-4855-9214-23f9bb909ded
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job cd720625-0c37-4855-9214-23f9bb909ded
Job cd720625-0c37-4855-9214-23f9bb909ded erfolgreich beendet durch Worker 4513d2c1-de79-48b3-bdb1-69dfec4e4c6e
Recovery: Job hat nicht den Status RUNNING sondern SUCCEEDED, Recovery wird abgebrochen
```

## DatabaseRetryTest.jobCreationRecoversAfterTransientDbOutage() (Z-6 (a))
```log
Datenbankverbindung unterbrochen
Starte Versuch: 1 für Aufruf: backend.api.JobController$$Lambda/0x000001f42506a0b0
2026-08-24T18:54:11.298+02:00  WARN 11360 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HHH000247: ErrorCode: 0, SQLState: null
2026-08-24T18:54:11.298+02:00  WARN 11360 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HikariPool-1 - Connection is not available, request timed out after 1008ms (total=0, active=0, idle=0, waiting=0)
Warte 200ms bis zum nächsten Retry...
Starte Versuch: 2 für Aufruf: backend.api.JobController$$Lambda/0x000001f42506a0b0
2026-08-24T18:54:12.521+02:00  WARN 11360 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HHH000247: ErrorCode: 0, SQLState: null
2026-08-24T18:54:12.521+02:00  WARN 11360 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HikariPool-1 - Connection is not available, request timed out after 1010ms (total=0, active=0, idle=0, waiting=0)
Warte 400ms bis zum nächsten Retry...
Starte Versuch: 3 für Aufruf: backend.api.JobController$$Lambda/0x000001f42506a0b0
2026-08-24T18:54:13.937+02:00  WARN 11360 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HHH000247: ErrorCode: 0, SQLState: null
2026-08-24T18:54:13.937+02:00  WARN 11360 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HikariPool-1 - Connection is not available, request timed out after 1009ms (total=0, active=0, idle=0, waiting=0)
Warte 800ms bis zum nächsten Retry...
Datenbankverbindung wiederhergestellt
Starte Versuch: 4 für Aufruf: backend.api.JobController$$Lambda/0x000001f42506a0b0
Response: 201 CREATED 
Created at 2026-08-24T16:54:14.764452Z
```

## DatabaseRetryTest.jobCreationFailsAfterExhaustedRetriesOnPermanentOutage() (Z-6 (a Gegenprobe))
```log
Starte Versuch: 1 für Aufruf: backend.api.JobController$$Lambda/0x0000013e9903d348
2026-08-24T19:13:14.091+02:00  WARN 21988 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HHH000247: ErrorCode: 0, SQLState: null
2026-08-24T19:13:14.091+02:00  WARN 21988 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HikariPool-1 - Connection is not available, request timed out after 1003ms (total=0, active=0, idle=0, waiting=0)
Warte 200ms bis zum nächsten Retry...
Starte Versuch: 2 für Aufruf: backend.api.JobController$$Lambda/0x0000013e9903d348
2026-08-24T19:13:15.310+02:00  WARN 21988 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HHH000247: ErrorCode: 0, SQLState: null
2026-08-24T19:13:15.310+02:00  WARN 21988 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HikariPool-1 - Connection is not available, request timed out after 1001ms (total=0, active=0, idle=0, waiting=0)
Warte 400ms bis zum nächsten Retry...
Starte Versuch: 3 für Aufruf: backend.api.JobController$$Lambda/0x0000013e9903d348
2026-08-24T19:13:16.715+02:00  WARN 21988 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HHH000247: ErrorCode: 0, SQLState: null
2026-08-24T19:13:16.715+02:00  WARN 21988 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HikariPool-1 - Connection is not available, request timed out after 1000ms (total=0, active=0, idle=0, waiting=0)
Warte 800ms bis zum nächsten Retry...
Starte Versuch: 4 für Aufruf: backend.api.JobController$$Lambda/0x0000013e9903d348
2026-08-24T19:13:18.528+02:00  WARN 21988 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HHH000247: ErrorCode: 0, SQLState: null
2026-08-24T19:13:18.528+02:00  WARN 21988 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HikariPool-1 - Connection is not available, request timed out after 1011ms (total=0, active=0, idle=0, waiting=0)
Warte 1600ms bis zum nächsten Retry...
Starte Versuch: 5 für Aufruf: backend.api.JobController$$Lambda/0x0000013e9903d348
2026-08-24T19:13:21.139+02:00  WARN 21988 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HHH000247: ErrorCode: 0, SQLState: null
2026-08-24T19:13:21.139+02:00  WARN 21988 --- [JobProcessing] [o-auto-1-exec-1] org.hibernate.orm.jdbc.error             : HikariPool-1 - Connection is not available, request timed out after 1007ms (total=0, active=0, idle=0, waiting=0)
Policy lehnt einen neuen Verarbeitungsversuch für Aufruf: backend.api.JobController$$Lambda/0x0000013e9903d348 ab.
Letzter Versuch scheiterte an: Could not open JPA EntityManager for transaction
```

## DatabaseRetryTest.testRetryNotAppliedOnConstraintViolation() (Z-6 (b))
```log
Starte Versuch: 1 für Aufruf: backend.api.JobController$$Lambda/0x000001c23b048000
Job erstellt
Starte Versuch: 1 für Aufruf: backend.api.JobController$$Lambda/0x000001c23b048000
2026-08-24T19:14:44.232+02:00  WARN 22272 --- [JobProcessing] [o-auto-1-exec-2] org.hibernate.orm.jdbc.error             : HHH000247: ErrorCode: 0, SQLState: 23505
2026-08-24T19:14:44.232+02:00  WARN 22272 --- [JobProcessing] [o-auto-1-exec-2] org.hibernate.orm.jdbc.error             : ERROR: duplicate key value violates unique constraint "uc_jobs_idempotencykey"
  Detail: Key (idempotency_key)=(2cdaa5ff-fc4e-43d6-a2eb-b42b6f04b756) already exists.
Policy lehnt einen neuen Verarbeitungsversuch für Aufruf: backend.api.JobController$$Lambda/0x000001c23b048000 ab.
Letzter Versuch scheiterte an: could not execute statement [ERROR: duplicate key value violates unique constraint "uc_jobs_idempotencykey"
  Detail: Key (idempotency_key)=(2cdaa5ff-fc4e-43d6-a2eb-b42b6f04b756) already exists.] [insert into jobs (attempt_count,claimed_by,created_at,idempotency_key,lease_until,payload,status,updated_at,id) values (?,?,?,?,?,?,?,?,?)]; SQL [insert into jobs (attempt_count,claimed_by,created_at,idempotency_key,lease_until,payload,status,updated_at,id) values (?,?,?,?,?,?,?,?,?)]; constraint [uc_jobs_idempotencykey]
```

## JobRecoveryForWorkerCrashTest.jobSurvivesRealWorkerProcessCrash() (Z-7, Z-8, Z-9)
```log
Worker1 gestartet
Job erstellt

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.1.0)

2026-08-24T19:22:48.109+02:00  INFO 29596 --- [JobProcessing] [           main] backend.Application                      : Starting Application v0.0.1-SNAPSHOT using Java 21.0.11 with PID 29596 (C:\Users\murfe\Downloads\app\collahbrs\FaultTolerantJobProcessing\target\FaultTolerantJobProcessing-0.0.1-SNAPSHOT.jar started by murfe in C:\Users\murfe\Downloads\app\collahbrs\FaultTolerantJobProcessing)
2026-08-24T19:22:48.115+02:00  INFO 29596 --- [JobProcessing] [           main] backend.Application                      : No active profile set, falling back to 1 default profile: "default"
2026-08-24T19:22:49.021+02:00  INFO 29596 --- [JobProcessing] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2026-08-24T19:22:49.083+02:00  INFO 29596 --- [JobProcessing] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 53 ms. Found 2 JPA repository interfaces.
2026-08-24T19:22:49.698+02:00  INFO 29596 --- [JobProcessing] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat initialized with port 0 (http)
2026-08-24T19:22:49.711+02:00  INFO 29596 --- [JobProcessing] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-08-24T19:22:49.712+02:00  INFO 29596 --- [JobProcessing] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.22]
2026-08-24T19:22:49.740+02:00  INFO 29596 --- [JobProcessing] [           main] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 1545 ms
2026-08-24T19:22:50.103+02:00  INFO 29596 --- [JobProcessing] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2026-08-24T19:22:50.354+02:00  INFO 29596 --- [JobProcessing] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@4168f3d9
2026-08-24T19:22:50.356+02:00  INFO 29596 --- [JobProcessing] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2026-08-24T19:22:50.382+02:00  INFO 29596 --- [JobProcessing] [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:postgresql://localhost:58645/jobprocessing?loggerLevel=OFF (PostgreSQL 17.10)
2026-08-24T19:22:50.474+02:00  INFO 29596 --- [JobProcessing] [           main] o.f.core.internal.command.DbValidate     : Successfully validated 4 migrations (execution time 00:00.035s)
2026-08-24T19:22:50.511+02:00  INFO 29596 --- [JobProcessing] [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "public": 4
2026-08-24T19:22:50.515+02:00  INFO 29596 --- [JobProcessing] [           main] o.f.core.internal.command.DbMigrate      : Schema "public" is up to date. No migration necessary.
2026-08-24T19:22:50.676+02:00  INFO 29596 --- [JobProcessing] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
2026-08-24T19:22:50.741+02:00  INFO 29596 --- [JobProcessing] [           main] org.hibernate.orm.core                   : HHH000001: Hibernate ORM core version 7.4.1.Final
2026-08-24T19:22:51.131+02:00  INFO 29596 --- [JobProcessing] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2026-08-24T19:22:51.204+02:00  INFO 29596 --- [JobProcessing] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
	Database JDBC URL [jdbc:postgresql://localhost:58645/jobprocessing?loggerLevel=OFF]
	Database driver: PostgreSQL JDBC Driver
	Database dialect: PostgreSQLDialect
	Database version: 17.10
	Default catalog/schema: jobprocessing/public
	Autocommit mode: undefined/unknown
	Isolation level: READ_COMMITTED [default READ_COMMITTED]
	JDBC fetch size: none
	Pool: DataSourceConnectionProvider
	Minimum pool size: undefined/unknown
	Maximum pool size: undefined/unknown
2026-08-24T19:22:52.014+02:00  INFO 29596 --- [JobProcessing] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-08-24T19:22:52.054+02:00  INFO 29596 --- [JobProcessing] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-08-24T19:22:52.142+02:00  INFO 29596 --- [JobProcessing] [           main] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
Starte Versuch: 1 f�r Aufruf: backend.worker.Worker$$Lambda/0x0000018c81b96808
2026-08-24T19:22:52.946+02:00  INFO 29596 --- [JobProcessing] [pool-2-thread-1] o.s.b.f.s.DefaultListableBeanFactory     : Obtaining singleton bean 'transactionManager' in thread "pool-2-thread-1" while other thread holds singleton lock for other beans [simpleAsyncTaskExecutorBuilder]
2026-08-24T19:22:52.949+02:00  INFO 29596 --- [JobProcessing] [           main] o.s.b.f.s.DefaultListableBeanFactory     : Obtaining singleton bean 'defaultValidator' in thread "main" while other thread holds singleton lock for other beans [platformTransactionManagerCustomizers]
2026-08-24T19:22:52.990+02:00  WARN 29596 --- [JobProcessing] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2026-08-24T19:22:53.014+02:00  INFO 29596 --- [JobProcessing] [pool-2-thread-1] o.s.b.f.s.DefaultListableBeanFactory     : Obtaining singleton bean 'org.springframework.data.jpa.repository.support.JpaEvaluationContextExtension' in thread "pool-2-thread-1" while other thread holds singleton lock for other beans [welcomePageHandlerMapping]
Job: Zustands�bergang von QUEUED nach RUNNING f�r Job 0ed7018e-0237-4cf9-b333-2746ca09d6e7
Worker: e1c36102-4cf7-469f-9bca-72a1c5fef337 starte Verarbeitung von Job: 0ed7018e-0237-4cf9-b333-2746ca09d6e7
Job beansprucht durch Worker mit ID: e1c36102-4cf7-469f-9bca-72a1c5fef337
Worker1 abgebrochen
Status des von Worker1 bearbeiteten Jobs: RUNNING
Worker2 gestartet

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.1.0)

2026-08-24T19:22:54.446+02:00  INFO 12024 --- [JobProcessing] [           main] backend.Application                      : Starting Application v0.0.1-SNAPSHOT using Java 21.0.11 with PID 12024 (C:\Users\murfe\Downloads\app\collahbrs\FaultTolerantJobProcessing\target\FaultTolerantJobProcessing-0.0.1-SNAPSHOT.jar started by murfe in C:\Users\murfe\Downloads\app\collahbrs\FaultTolerantJobProcessing)
2026-08-24T19:22:54.452+02:00  INFO 12024 --- [JobProcessing] [           main] backend.Application                      : No active profile set, falling back to 1 default profile: "default"
2026-08-24T19:22:55.386+02:00  INFO 12024 --- [JobProcessing] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2026-08-24T19:22:55.438+02:00  INFO 12024 --- [JobProcessing] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 44 ms. Found 2 JPA repository interfaces.
2026-08-24T19:22:56.054+02:00  INFO 12024 --- [JobProcessing] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat initialized with port 0 (http)
2026-08-24T19:22:56.068+02:00  INFO 12024 --- [JobProcessing] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-08-24T19:22:56.069+02:00  INFO 12024 --- [JobProcessing] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.22]
2026-08-24T19:22:56.096+02:00  INFO 12024 --- [JobProcessing] [           main] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 1567 ms
2026-08-24T19:22:56.453+02:00  INFO 12024 --- [JobProcessing] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2026-08-24T19:22:56.708+02:00  INFO 12024 --- [JobProcessing] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@4168f3d9
2026-08-24T19:22:56.709+02:00  INFO 12024 --- [JobProcessing] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2026-08-24T19:22:56.735+02:00  INFO 12024 --- [JobProcessing] [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:postgresql://localhost:58645/jobprocessing?loggerLevel=OFF (PostgreSQL 17.10)
2026-08-24T19:22:56.832+02:00  INFO 12024 --- [JobProcessing] [           main] o.f.core.internal.command.DbValidate     : Successfully validated 4 migrations (execution time 00:00.039s)
2026-08-24T19:22:56.862+02:00  INFO 12024 --- [JobProcessing] [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "public": 4
2026-08-24T19:22:56.869+02:00  INFO 12024 --- [JobProcessing] [           main] o.f.core.internal.command.DbMigrate      : Schema "public" is up to date. No migration necessary.
2026-08-24T19:22:57.039+02:00  INFO 12024 --- [JobProcessing] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
2026-08-24T19:22:57.106+02:00  INFO 12024 --- [JobProcessing] [           main] org.hibernate.orm.core                   : HHH000001: Hibernate ORM core version 7.4.1.Final
2026-08-24T19:22:57.518+02:00  INFO 12024 --- [JobProcessing] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2026-08-24T19:22:57.594+02:00  INFO 12024 --- [JobProcessing] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
	Database JDBC URL [jdbc:postgresql://localhost:58645/jobprocessing?loggerLevel=OFF]
	Database driver: PostgreSQL JDBC Driver
	Database dialect: PostgreSQLDialect
	Database version: 17.10
	Default catalog/schema: jobprocessing/public
	Autocommit mode: undefined/unknown
	Isolation level: READ_COMMITTED [default READ_COMMITTED]
	JDBC fetch size: none
	Pool: DataSourceConnectionProvider
	Minimum pool size: undefined/unknown
	Maximum pool size: undefined/unknown
2026-08-24T19:22:58.386+02:00  INFO 12024 --- [JobProcessing] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-08-24T19:22:58.433+02:00  INFO 12024 --- [JobProcessing] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-08-24T19:22:58.517+02:00  INFO 12024 --- [JobProcessing] [           main] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
Starte Versuch: 1 f�r Aufruf: backend.worker.Worker$$Lambda/0x0000018b23b91c20
2026-08-24T19:22:59.329+02:00  INFO 12024 --- [JobProcessing] [pool-2-thread-1] o.s.b.f.s.DefaultListableBeanFactory     : Obtaining singleton bean 'transactionManager' in thread "pool-2-thread-1" while other thread holds singleton lock for other beans [simpleAsyncTaskExecutorBuilder]
2026-08-24T19:22:59.332+02:00  INFO 12024 --- [JobProcessing] [           main] o.s.b.f.s.DefaultListableBeanFactory     : Obtaining singleton bean 'org.springframework.boot.validation.autoconfigure.ValidationAutoConfiguration' in thread "main" while other thread holds singleton lock for other beans [platformTransactionManagerCustomizers]
2026-08-24T19:22:59.332+02:00  INFO 12024 --- [JobProcessing] [           main] o.s.b.f.s.DefaultListableBeanFactory     : Obtaining singleton bean 'defaultValidator' in thread "main" while other thread holds singleton lock for other beans [platformTransactionManagerCustomizers]
2026-08-24T19:22:59.386+02:00  WARN 12024 --- [JobProcessing] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2026-08-24T19:22:59.399+02:00  INFO 12024 --- [JobProcessing] [pool-2-thread-1] o.s.b.f.s.DefaultListableBeanFactory     : Obtaining singleton bean 'org.springframework.data.jpa.repository.support.JpaEvaluationContextExtension' in thread "pool-2-thread-1" while other thread holds singleton lock for other beans [mvcConversionService, welcomePageHandlerMapping]
Worker: d7d75d72-02af-4e47-854e-2e538f7d256b kein Job verf�gbar
2026-08-24T19:22:59.860+02:00  INFO 12024 --- [JobProcessing] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 58676 (http) with context path '/'
2026-08-24T19:22:59.873+02:00  INFO 12024 --- [JobProcessing] [           main] backend.Application                      : Started Application in 5.945 seconds (process running for 6.53)
Starte Versuch: 1 f�r Aufruf: backend.worker.Worker$$Lambda/0x0000018b23b91c20
Worker: d7d75d72-02af-4e47-854e-2e538f7d256b kein Job verf�gbar
Starte Versuch: 1 f�r Aufruf: backend.worker.Worker$$Lambda/0x0000018b23b91c20
Worker: d7d75d72-02af-4e47-854e-2e538f7d256b kein Job verf�gbar
Starte Versuch: 1 f�r Aufruf: backend.worker.Worker$$Lambda/0x0000018b23b91c20
Worker: d7d75d72-02af-4e47-854e-2e538f7d256b kein Job verf�gbar
Starte Versuch: 1 f�r Aufruf: backend.worker.Worker$$Lambda/0x0000018b23b91c20
Worker: d7d75d72-02af-4e47-854e-2e538f7d256b kein Job verf�gbar
Starte Versuch: 1 f�r Aufruf: backend.worker.Worker$$Lambda/0x0000018b23b91c20
Worker: d7d75d72-02af-4e47-854e-2e538f7d256b kein Job verf�gbar
Job: Zustands�bergang von RUNNING nach QUEUED f�r Job 0ed7018e-0237-4cf9-b333-2746ca09d6e7
Recovery: Job 0ed7018e-0237-4cf9-b333-2746ca09d6e7 recovered und Status zur�ck auf QUEUED gesetzt
Starte Versuch: 1 f�r Aufruf: backend.worker.Worker$$Lambda/0x0000018b23b91c20
Job: Zustands�bergang von QUEUED nach RUNNING f�r Job 0ed7018e-0237-4cf9-b333-2746ca09d6e7
Worker: d7d75d72-02af-4e47-854e-2e538f7d256b starte Verarbeitung von Job: 0ed7018e-0237-4cf9-b333-2746ca09d6e7
Starte Versuch: 1 f�r Aufruf: backend.worker.Worker$$Lambda/0x0000018b23c7a400
Job: Zustands�bergang von RUNNING nach SUCCEEDED f�r Job 0ed7018e-0237-4cf9-b333-2746ca09d6e7
Job 0ed7018e-0237-4cf9-b333-2746ca09d6e7 erfolgreich beendet durch Worker d7d75d72-02af-4e47-854e-2e538f7d256b
Worker: d7d75d72-02af-4e47-854e-2e538f7d256b hat Job: 0ed7018e-0237-4cf9-b333-2746ca09d6e7 erfolgreich beendet
Starte Versuch: 1 f�r Aufruf: backend.worker.Worker$$Lambda/0x0000018b23b91c20
Worker: d7d75d72-02af-4e47-854e-2e538f7d256b kein Job verf�gbar
Job erfolgreich abgeschlossen
SUCCEEDED
{"result": "Testergebnis", "Zeitpunkt": "2026-08-24T17:23:16.614501300Z"}
```

## WorkerCrashRecoveryTest.recoveryComponentTest() (Z-8 (a))
```log
Job: Zustandsübergang von QUEUED nach RUNNING für Job b438bf74-171b-4d53-8d4a-aa3090859477
Job: Zustandsübergang von RUNNING nach QUEUED für Job b438bf74-171b-4d53-8d4a-aa3090859477
Recovery: Job b438bf74-171b-4d53-8d4a-aa3090859477 recovered und Status zurück auf QUEUED gesetzt
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000021ec1cf9130
Job: Zustandsübergang von QUEUED nach RUNNING für Job b438bf74-171b-4d53-8d4a-aa3090859477
Worker: b011617c-cd10-4bac-ac5e-ba8018796ced starte Verarbeitung von Job: b438bf74-171b-4d53-8d4a-aa3090859477
Simulierter Zugriff eines unberechtigten Workers:
Worker hat keine Berechtigung, diesen Job zu bearbeiten. Besitzer: b011617c-cd10-4bac-ac5e-ba8018796ced Worker: 41a043b8-154a-445c-8777-5dc443ac1bf8
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000021ec1e34000
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job b438bf74-171b-4d53-8d4a-aa3090859477
Job b438bf74-171b-4d53-8d4a-aa3090859477 erfolgreich beendet durch Worker b011617c-cd10-4bac-ac5e-ba8018796ced
Worker: b011617c-cd10-4bac-ac5e-ba8018796ced hat Job: b438bf74-171b-4d53-8d4a-aa3090859477 erfolgreich beendet
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000021ec1cf9130
Worker: b011617c-cd10-4bac-ac5e-ba8018796ced kein Job verfügbar
Simulierter Zugriff eines zu langsamen Workers:
WorkerService: Job hat nicht den Status RUNNING sondern SUCCEEDED, obwohl Worker Ergebnis speichern wollte
```

## WorkerCrashRecoveryTest.finishRejectedWhenLeaseExpiredButNotYetRecovered() (Z-8 (b))
```log
Job: Zustandsübergang von QUEUED nach RUNNING für Job d7af152b-f6bd-4ffd-8d81-6a9be1ebfa5f
Job: Zustandsübergang von QUEUED nach RUNNING für Job 83273539-1521-4ea7-aa42-221f70c70329
Lease abgelaufen! Worker hat keine Berechtigung mehr, diesen Job zu bearbeiten. Worker: b499dba8-3bb9-457b-b7e3-3658097f2804
```

## JobTerminationTest.terminationAfterTooManyFailuresTest() (Z-9)
```log
Test gestartet. Idempotenz-Key: 7d711c92-85fe-42a7-ae4e-b84553950338
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Job: Zustandsübergang von QUEUED nach RUNNING für Job 2f7f1c94-5bcf-43db-bbe9-d58ec68eab68
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad starte Verarbeitung von Job: 2f7f1c94-5bcf-43db-bbe9-d58ec68eab68
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001e52000
Job: Zustandsübergang von RUNNING nach SUCCEEDED für Job 2f7f1c94-5bcf-43db-bbe9-d58ec68eab68
Job 2f7f1c94-5bcf-43db-bbe9-d58ec68eab68 erfolgreich beendet durch Worker 6127375f-11a9-4634-9fab-0003a9ca67ad
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad hat Job: 2f7f1c94-5bcf-43db-bbe9-d58ec68eab68 erfolgreich beendet
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad kein Job verfügbar
Sanity-Check erfolgreich abgeschlossen
Status QUEUED
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7d711c92-85fe-42a7-ae4e-b84553950338
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad starte Verarbeitung von Job: 7d711c92-85fe-42a7-ae4e-b84553950338
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad Verarbeitung Fehlgeschlagen mit Error: Simulierter permanenter Fachfehler
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad kein Job verfügbar
Job: Zustandsübergang von RUNNING nach QUEUED für Job 7d711c92-85fe-42a7-ae4e-b84553950338
Recovery: Job 7d711c92-85fe-42a7-ae4e-b84553950338 recovered und Status zurück auf QUEUED gesetzt
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7d711c92-85fe-42a7-ae4e-b84553950338
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad starte Verarbeitung von Job: 7d711c92-85fe-42a7-ae4e-b84553950338
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad Verarbeitung Fehlgeschlagen mit Error: Simulierter permanenter Fachfehler
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad kein Job verfügbar
Job: Zustandsübergang von RUNNING nach QUEUED für Job 7d711c92-85fe-42a7-ae4e-b84553950338
Recovery: Job 7d711c92-85fe-42a7-ae4e-b84553950338 recovered und Status zurück auf QUEUED gesetzt
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7d711c92-85fe-42a7-ae4e-b84553950338
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad starte Verarbeitung von Job: 7d711c92-85fe-42a7-ae4e-b84553950338
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad Verarbeitung Fehlgeschlagen mit Error: Simulierter permanenter Fachfehler
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad kein Job verfügbar
Job: Zustandsübergang von RUNNING nach QUEUED für Job 7d711c92-85fe-42a7-ae4e-b84553950338
Recovery: Job 7d711c92-85fe-42a7-ae4e-b84553950338 recovered und Status zurück auf QUEUED gesetzt
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7d711c92-85fe-42a7-ae4e-b84553950338
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad starte Verarbeitung von Job: 7d711c92-85fe-42a7-ae4e-b84553950338
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad Verarbeitung Fehlgeschlagen mit Error: Simulierter permanenter Fachfehler
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad kein Job verfügbar
Job: Zustandsübergang von RUNNING nach QUEUED für Job 7d711c92-85fe-42a7-ae4e-b84553950338
Recovery: Job 7d711c92-85fe-42a7-ae4e-b84553950338 recovered und Status zurück auf QUEUED gesetzt
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Job: Zustandsübergang von QUEUED nach RUNNING für Job 7d711c92-85fe-42a7-ae4e-b84553950338
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad starte Verarbeitung von Job: 7d711c92-85fe-42a7-ae4e-b84553950338
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad Verarbeitung Fehlgeschlagen mit Error: Simulierter permanenter Fachfehler
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad kein Job verfügbar
Job: Zustandsübergang von RUNNING nach FAILED für Job 7d711c92-85fe-42a7-ae4e-b84553950338
Recovery: Job 7d711c92-85fe-42a7-ae4e-b84553950338 wird aufgrund zu vieler Versuche als FAILED markiert
Starte Versuch: 1 für Aufruf: backend.worker.Worker$$Lambda/0x0000022001d1dd40
Worker: 6127375f-11a9-4634-9fab-0003a9ca67ad kein Job verfügbar
Es dauerte 12.179 Sekunden, bis der Job abgeschlossen wurde
```
