# PicaVox
Android app that gets automatically pushed to internal testing

## GitHub Actions setup for Google Play Internal Testing

Configure these repository secrets before pushing to `main` or running `workflow_dispatch`:

- `GOOGLE_PLAY_SERVICE_ACCOUNT_JSON`: Full JSON key for a Google Play service account with release access.
- `KEYSTORE_BASE64`: Base64-encoded Android release keystore file contents.
- `STORE_PASSWORD`: Android release keystore password.
- `KEY_ALIAS`: Android release key alias.
- `KEY_PASSWORD`: Android release key password.

The workflow deploys the app with package name `com.picavox.app`.

Workflow behavior:

- Pull requests to `main`: builds `bundleRelease`.
- Pushes to `main`: validates release secrets, builds `bundleRelease`, uploads the generated `.aab`, and deploys it to Google Play Internal Testing.
- Manual runs (`workflow_dispatch`): validates release secrets, builds `bundleRelease`, uploads the generated `.aab`, and deploys it to Google Play Internal Testing.
