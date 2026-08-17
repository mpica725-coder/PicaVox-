# PicaVox
Android app that gets automatically pushed to internal testing

## GitHub Actions setup for Google Play Internal Testing

Configure these repository secrets before pushing to `main`:

- `GOOGLE_PLAY_SERVICE_ACCOUNT_JSON`: Full JSON key for a Google Play service account with release access.
- `GOOGLE_PLAY_PACKAGE_NAME`: Android application ID (package name), for example `com.example.app`.

Workflow behavior:

- Pull requests to `main`: builds `bundleRelease`.
- Pushes to `main`: builds `bundleRelease` and uploads the generated `.aab` to Google Play Internal Testing.

## Download

Once the app is signed and built, the release bundle is available as a GitHub Actions artifact:

👉 [Download latest signed release (release-aab)](https://github.com/mpica725-coder/PicaVox-/actions/workflows/main.yml?query=branch%3Amain+event%3Apush)

Click the most recent successful run on `main`, then download the **release-aab** artifact.
