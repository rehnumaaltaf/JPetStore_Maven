# OlamCTRM

Olam CTRM Angular Project.

# Features
- Ready to go, statically typed build system using gulp for working with TypeScript.
- Production and development builds.
- **Ahead-of-Time** with **Tree-Shaking** production builds with Rollup compilation support.
- Unit tests with Jasmine and [Karma](https://karma-runner.github.io). including code coverage via [istanbul](https://gotwarlost.github.io/istanbul/).
- End-to-end tests with [Protractor](http://www.protractortest.org/).
- Following the [best practices](https://angular.io/styleguide).

## Build

Run `npm run build:dev` to build the project for development. The build artifacts will be stored in the `dist/` directory. Use the `npm run build:prod` for a production build.

## Development server

Run `npm run start` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files. 

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|module`.

## Running unit tests

Run `npm run test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `npm run e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
Before running the tests make sure you are serving the app via `npm run start`.

## Running lint

Run `npm run lint` to execute the static code analysis on the code.

## Running Code Coverage

Run `npm run test:coverage` to execute the testing code coverage via Istanbul.

## Naming conventions
The following table is shown the naming conventions for every element:

Element | Naming style | Example | usage
----|------|----|--------
Modules | lowerCamelCase  | angularApp |
Controllers | Functionality + 'Ctrl'  | AdminCtrl |
Directives | lowerCamelCase  | userInfo |
Filters | lowerCamelCase | userFilter |
Services | UpperCamelCase | User | constructor
Factories | lowerCamelCase | dataFactory | others

## Localization
Angular localization [Demo](https://robisim74.github.io/angular-l10n-sample/#/home)

Following links for reference & code snippets
-[Quick start](https://github.com/robisim74/angular-l10n/blob/master/doc/quick-start.md)
-[Library specification](https://github.com/robisim74/angular-l10n/blob/master/doc/spec.md)
-[Snippets](https://github.com/robisim74/angular-l10n/wiki/Snippets)

## Further help
