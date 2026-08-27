<a name="readme-top"></a>

<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![CodeFactor][codefactor-shield]][codefactor-url]
[![Codacy][codacy-shield]][codacy-url]
[![Issues][issues-shield]][issues-url]
[![AGPL-3.0 License][license-shield]][license-url]
![Java][java-shield]

<!-- PROJECT HEADER -->
<br />
<div align="center">

<h3 align="center">Citadelles</h3>

  <p align="center">
    A terminal rebuild of the board game Citadels in plain Java — 8 characters,
    14 wonders, robot opponents, no framework and no build tool.
    <br />
    <br />
    <a href="https://github.com/GabinSMD/citadelles/issues/new?assignees=&labels=&template=bug_report.md&title=">Report Bug</a>
    ·
    <a href="https://github.com/GabinSMD/citadelles/issues/new?assignees=&labels=&template=feature_request.md&title=">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#whats-implemented">What's implemented</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#architecture">Architecture</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#compile-and-run">Compile and run</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#tests">Tests</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

This project was built as part of our studies at [esaip](https://www.esaip.org/): produce a digital
version of the board game **Citadels**. Given the time allocated, the result is a game you play in a
terminal, against robot opponents, with the character powers and the scoring implemented by hand.

There is no framework, no dependency and no build tool — 36 Java files, `javac`, and the standard
library. The project ships as an Eclipse project (`.classpath`, `.project`) targeting **Java 17**.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### What's implemented

| | |
| --- | --- |
| **Characters** | 8 — Assassin, Voleur, Magicienne, Roi, Évêque, Marchande, Condottiere, Architecte, each with its power |
| **Districts** | 5 families — religious, military, noble, trade, and wonders |
| **Wonders** | 14, with their special effects (Bibliothèque, Carrière, Cours des miracles, Donjon, Dracoport…) |
| **Players** | 4, the project's target; the end-of-game rule is written for 4 to 7 |
| **Opponents** | robot players, so a single human can play a full game |
| **Scoring** | construction cost, wonders, completed city, one-of-each-type bonus |
| **Interface** | terminal, with ANSI colours |

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* **Java 17** — standard library only, no external dependency
* **Eclipse** — the repository is an Eclipse project; any IDE or a bare `javac` works too

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ARCHITECTURE -->
## Architecture

A deliberate MVC split, which is also what the assignment asked for:

```
src/
├── application/   Application (entry point), Jeu (turn loop and scoring), Configuration (the deck)
├── controleur/    Interaction — everything read from and printed to the terminal
├── modele/        Personnage and its 8 subclasses, Joueur, Quartier, Pioche,
│                  PlateauDeJeu, Caracteristiques (the wonders' effects)
└── test/          16 test classes, one per model class
```

Only `controleur/` talks to the terminal: the model knows nothing about input or output, which is what
makes the test classes possible without a console harness.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

* A **JDK 17** or later
  ```sh
  javac --version
  ```

### Compile and run

1. Clone the repository
   ```sh
   git clone https://github.com/GabinSMD/citadelles.git
   cd citadelles
   ```
2. Compile every source file into `bin/`
   ```sh
   javac -encoding UTF-8 -d bin $(find src -name '*.java')
   ```
3. Play
   ```sh
   java -cp bin application.Application
   ```

In Eclipse, importing the folder as an existing project is enough — `.classpath` already points `src`
at `bin`.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE -->
## Usage

The game runs entirely in the terminal: it prints the board, the hands and the districts built, then
prompts for each choice — pick a character, take gold or draw districts, build, use your power. Use a
terminal that renders ANSI escape codes, otherwise the colours show up as raw characters.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- TESTS -->
## Tests

`src/test/` holds 16 test classes, one per model class (`TestRoi`, `TestAssassin`, `TestPioche`…).
They are **not** JUnit: each exposes its own `main` and prints its assertions, so they run like any
other class.

```sh
java -cp bin test.TestRoi
```

Turning them into JUnit tests is the single most useful thing anyone could contribute here — see the
roadmap.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- [ ] Graphical interface (announced when the project was handed in, never built)
- [ ] Migrate `src/test/` to JUnit 5 and add a build tool (Maven or Gradle)
- [ ] Support 5 to 7 players end to end, since the scoring already handles them
- [ ] Human-versus-human play on the same terminal

See the [open issues](https://github.com/GabinSMD/citadelles/issues) for anything else.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

A finished school project, kept online as a record — but pull requests are welcome, particularly on
the roadmap items above.

1. Fork the project
2. Create your branch (`git checkout -b feature/junit-migration`)
3. Commit your changes (`git commit -m 'Migrate TestRoi to JUnit 5'`)
4. Push to the branch and open a pull request

Please read [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) and [SECURITY.md](SECURITY.md) first.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the GNU Affero General Public License v3.0. See [`LICENSE`](LICENSE) for the full
text.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Gabin Simond — gabin.simond@simondancebros.org

Project link: [https://github.com/GabinSMD/citadelles](https://github.com/GabinSMD/citadelles)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* **Citadels** by Bruno Faidutti — the board game this reimplements
* [esaip](https://www.esaip.org/) — where the assignment came from
* [@Arahord](https://github.com/Arahord) and [@C0sinuS](https://github.com/C0sinuS) — the other two thirds of the commits
* [Best-README-Template](https://github.com/othneildrew/Best-README-Template) — the shape of this file

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/GabinSMD/citadelles?style=for-the-badge
[contributors-url]: https://github.com/GabinSMD/citadelles/graphs/contributors
[codefactor-shield]: https://img.shields.io/codefactor/grade/github/gabinsmd/citadelles?label=CodeFactor&style=for-the-badge
[codefactor-url]: https://www.codefactor.io/repository/github/gabinsmd/citadelles
[codacy-shield]: https://img.shields.io/codacy/grade/e5241157c34f40bcb23b37fa098a5622?label=Codacy&style=for-the-badge
[codacy-url]: https://app.codacy.com/gh/GabinSMD/citadelles/
[issues-shield]: https://img.shields.io/github/issues/GabinSMD/citadelles?style=for-the-badge
[issues-url]: https://github.com/GabinSMD/citadelles/issues
[license-shield]: https://img.shields.io/badge/license-AGPL%20v3-blue.svg?style=for-the-badge
[license-url]: https://github.com/GabinSMD/citadelles/blob/main/LICENSE
[java-shield]: https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
