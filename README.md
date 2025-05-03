# PricePal Android
This is the Android repository for the "PricePal" project.

<br/>

<p align="center">
  <img src="https://github.com/user-attachments/assets/24e540a3-9f1c-402c-9723-6628152484aa" width="400" height="400">
</p>
<br/>

**📌 Checking progress**

- To see detailed progress on **Notion**. -> 
  [![Notion](https://img.shields.io/badge/Notion-000000?style=flat-square&logo=notion&logoColor=white)](https://bumpy-thumb-c82.notion.site/FE-1c9009a1b2e980a0912de9f8619ef075?pvs=4)
<br>

### 🙌 Introduce FE Developers

|나현주(Hyeonjoo Na)|홍지현(Jihyun Hong)|
|:---:|:---:|
|<img src="https://github.com/HyeonJooooo.png"  width="300" height="230">|<img src="https://github.com/Hongji03.png"  width="300" height="230">|
|[@HyeonJooooo](https://github.com/HyeonJooooo)|[@Hongji03](https://github.com/Hongji03)|
<br/>

# Tech Stack

Here is a table summarizing the tech stack used for implementing the project.

<div align=center>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Detail</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Kotlin</td>
                <td>Programming Language</td>
            </tr>
            <tr>
                <td>Jetpack Compose</td>
                <td>In-code declarative app architecture</td>
            </tr>
            <tr>
                <td>Git</td>
                <td>Systematic code management and collaboration</td>
            </tr>
        </tbody>
    </table>
</div>

This project includes the following library dependencies.

<div align=center>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Version</th>
                <th>Detail</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Jetpack Navigation</td>
                <td>2.0.21</td>
                <td>A library for screen transition management</td>
            </tr>
            <tr>
                <td>Hilt</td>
                <td>2.51.1</td>
                <td>A library for dependency injection</td>
            </tr>
            <tr>
                <td>Retrofit2</td>
                <td>2.11.0</td>
                <td>A library for HTTP communication</td>
            </tr>
            <tr>
                <td>SharedPreference</td>
                <td>1.2.1</td>
                <td>A library for local data storage</td>
            </tr>
            <tr>
                <td>Room</td>
                <td>2.6.1</td>
                <td>Local database library</td>
            </tr>
            <tr>
                <td>Android JUnit</td>
                <td>1.2.1</td>
                <td>A library for unit testing</td>
            </tr>
        </tbody>
    </table>
</div>

This project is based on a multi-module architecture and follows the MVVM design pattern using ViewModels.

# Conventions

The following are the conventions that developers contributing to this project must follow.

## Branch

This project follows the Gitflow branch strategy.

<div align=center>
    <img src="https://techblog.woowahan.com/wp-content/uploads/img/2017-10-30/git-flow_overall_graph.png" width=50% alt="브랜치 전략 설명 이미지"/>
</div>

- `master`: Deployable branch unit
- `release`: A branch that allows testing before deployment
- `develop`: Development branch
- `feature/#issue_number`: Feature-specific branch
- `hotfix`: Hotfix branch for the master branch

All feature development follows the process below:

1. Create an issue for the feature to be developed and obtain an issue number.
2. Branch off from the `develop` branch and create a `feature` branch named using the issue number.
3. Once development is complete, open a pull request to the `develop` branch. After receiving team approval, merge the changes.

## Commit

Commits are written visually using [Gitmoji](https://gitmoji.dev/). Below is the commit format used in this project. Please note that an empty line is included between each section.

```text
[Gitmoji] [title]

[content]

[Issue reference (optional)]
```

예시)

```text
:bug: Fix button bug

Fixed an issue where the keyboard callback was not triggered.

Refs: #123, #234
```

Refer to [this blog](https://treasurebear.tistory.com/70) for the meaning of each Gitmoji.  
You can conveniently use Gitmoji with the [Gitmoji Plus: Commit Button](https://plugins.jetbrains.com/plugin/12383-gitmoji-plus-commit-button) plugin provided by Android Studio.

## Issue

Issues must be created using the appropriate issue template provided in this repository.

- `Feature Template`: Used for issues related to feature additions.
- `Bug Template`: Used for issues related to bug fixes.

## Pull Request

Pull requests must be created using the template provided in this repository.

## Code

The code style follows the [official Android Kotlin style guide](https://developer.android.com/kotlin/style-guide?hl=en) as closely as possible.  
Below are the key naming conventions:

- All source files must be encoded in UTF-8.
- Kotlin file names should use `PascalCase` whenever possible.
- Composable function names should use `PascalCase`. Other functions should start with a verb and use `camelCase`. Variable names should also follow `camelCase` (including variables storing lambda expressions).
- Variables that pass callback functions should start with `on`, e.g., `onButtonClicked`, `onDataLoaded`.
- Pay attention to yellow underlines in the Android Studio IDE.

# Environment

Here is the Android development environment for this project:

- `targetSDK`: 35, `minSDK`: 26
- Android Studio version: Ladybug | 2024.2.1 or higher
- Testing environment: Android Studio emulator (AVD)
- - Device name: Pixel 8
  - API 35 (Android 15.0, x86_64)
  - 1080 x 2400 px (412 x 915 dp)
