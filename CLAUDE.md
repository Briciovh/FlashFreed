# Claude Code Guidelines

## Dependencies

Never downgrade a dependency version. If a situation arises where a downgrade seems necessary, stop, explain the conflict, and wait for explicit approval before proceeding.

## After every code task

Run the following steps in order and report any failures before considering the task complete:

1. **Gradle sync** — `./gradlew --quiet dependencies`
2. **Build** — `./gradlew assembleDebug`
3. **Unit tests** — `./gradlew test`
