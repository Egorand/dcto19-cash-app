# dcto19-cash-app
Not a Cash App but whevs

This app serves as a testbed for experiments with incremental Kotlin compilation and kapt. Here's a rough outline of the project:

```
:app
apply plugin: 'com.android.application'
implementation project(':presenters')
implementation project(':backend:jvm')
```

```
:presenters
apply plugin: 'com.android.library'
api project(':backend:api')
```

```
:backend:api
apply plugin: 'org.jetbrains.kotlin.jvm'
```

```
:backend:jvm
apply plugin: 'com.android.library'
api project(':backend:api')
```

The following changes are applied to the codebase to monitor the performance of the Kotlin plugin:

1. Clean build.

Builds are always performed with the following command:

```
./gradlew :app:assembleDebug --profile
```

2. No changes.
3. Java change in :app.
4. Kotlin change in :app.
5. Implementation change in :presenters.
6. ABI change in :presenters.
7. ABI change in :backend:api.
8. Implementation change in :backend:jvm.
