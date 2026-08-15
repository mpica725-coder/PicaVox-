# PicaVox
Android app that gets automatically pushed to internal testing.

## CI

GitHub Actions runs the Android release bundle build on:
- pushes to `main`
- pull requests targeting `main`
- manual `workflow_dispatch`

The workflow builds `bundleRelease` and uploads the generated `.aab` as a downloadable artifact (`release-aab`).
