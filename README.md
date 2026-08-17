# PicaVox
Android app with a GitHub Actions workflow that builds a signed release bundle for manual Google Play upload.

## GitHub Actions setup for signed release bundles

Configure these repository secrets before running the workflow:

- `KEYSTORE_BASE64`: Base64-encoded contents of your release keystore file.
- `STORE_PASSWORD`: Keystore password.
- `KEY_ALIAS`: Release key alias.
- `KEY_PASSWORD`: Release key password.

Workflow behavior:

- Manual run (`workflow_dispatch`) or push to `main`: validates signing secrets, builds `bundleRelease`, and uploads the generated signed `.aab` as the `signed-release-aab` artifact.

## Download the signed bundle

1. Open the workflow run in GitHub Actions.
2. Download the `signed-release-aab` artifact.
3. Extract the artifact and use the `.aab` from `app/build/outputs/bundle/release/`.
4. Upload the bundle manually in Google Play Console for package `com.picavox.app`.
