[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]



<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/Boukhtam/react-native-eid-reader">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">react-native-eid-reader</h3>

  <p align="center">
    A react-native module/tool to read the contents of <a href="https://www.iso.org/standard/14732.html"> ISO7816 Identification/Smart cards </a>using the NFC chip.
    <br />
    <a href="https://github.com/Boukhtam/react-native-eid-reader"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/Boukhtam/react-native-eid-reader">View Demo</a>
    ·
    <a href="https://github.com/Boukhtam/react-native-eid-reader/issues">Report Bug</a>
    ·
    <a href="https://github.com/Boukhtam/react-native-eid-reader/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents


* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Mostly automatic installation](#mostly-automatic-installation)
  * [Manual installation](#manual-installation)
* [Usage](#usage)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)




<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]]

<!--The goal of this project is to allow react-native developers to implement .-->

The module/tool currently reads the contents of:

* Electronic/Biometric passports in BAC security mode.
* The Algerian eID card.

    A list of commonly used resources that I find helpful are listed in the acknowledgements.

### Built With
* [jMRTD](http://jmrtd.org/) - The library for reading the epassport chip [LGPL 3.0 License][lgpl-license].
* [SCUBA](http://scuba.sourceforge.net/) - a Java based framework for programming smart card aware host applications [LGPL 3.0 License][lgpl-license].


<!-- GETTING STARTED -->
## Getting Started


### Prerequisites

* [node & npm][nodejs-url]
* [React Native][react-native-url]

### Mostly automatic installation

1. Within your React Native project, open up a new terminal window and install the module:
```sh
$ npm install react-native-eid-reader --save
```

2. React Native requires linking native dependencies, excute the following in the terminal:
```sh
$ react-native link react-native-eid-reader
```

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-eid-reader` and add `EidReader.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libEidReader.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.EidReaderPackage;` to the imports at the top of the file
  - Add `new EidReaderPackage()` to the list returned by the `getPackages()` method
  2. Append the following lines to `android/settings.gradle`:
  ```
  include ':react-native-eid-reader'
  project(':react-native-eid-reader').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-eid-reader/android')
  ```
  3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  ```
  compile project(':react-native-eid-reader')
  ```


<!-- USAGE EXAMPLES -->
## Usage
```javascript
import EidReader from 'react-native-eid-reader';

// TODO: What to do with the module?
EidReader;
```

<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/Boukhtam/react-native-eid-reader/issues) for a list of proposed features (and known issues).


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<!-- LICENSE -->
## License

Distributed under the [Apache License 2.0]() License. See `LICENSE` for more information.

<!-- CONTACT -->
## Contact

Hamza BOUKHTAM - [@boukhtam_hamza](https://twitter.com/boukhtam_hamza) - xu@live.fr

Project Link: [React Native Electronic-Identity-Documents Reader module](https://github.com/Boukhtam/react-native-eid-reader)


<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Boukhtam/react-native-eid-reader?style=plastic
[contributors-url]: https://github.com/Boukhtam/react-native-eid-reader/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Boukhtam/react-native-eid-reader?style=plastic
[forks-url]: https://github.com/Boukhtam/react-native-eid-reader/network/members
[stars-shield]: https://img.shields.io/github/stars/Boukhtam/react-native-eid-reader?style=plastic
[stars-url]: https://github.com/Boukhtam/react-native-eid-reader/stargazers
[issues-shield]: https://img.shields.io/github/issues/Boukhtam/react-native-eid-reader?style=plastic
[issues-url]: https://github.com/Boukhtam/react-native-eid-reader/issues
[license-shield]: https://img.shields.io/github/license/Boukhtam/react-native-eid-reader?logo=apache&style=plastic
[license-url]: https://github.com/Boukhtam/react-native-eid-reader/blob/master/LICENSE
[sequence-diagram]: images/sequence_diagram.svg
[lgpl-license]: https://www.gnu.org/licenses/lgpl-3.0.en.html
[nodejs-url]: https://nodejs.org/en/
[react-native-url]: https://facebook.github.io/react-native/

