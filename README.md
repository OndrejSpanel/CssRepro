# CssRepro

Demonstrate an issue with Udash 0.9.0 and Scala 2.13 when using CrossProject plugin

## Repro

`sbt backend/run`

### Expected result

The application should print a short CSS file

### What happens instead

An empty string is printed

### Observation

- Variable `elementsBuffer` accessed in `CssRender.render` is empty.
- When you remove `JSDependenciesPlugin` and do a clean build, Css is printed fine




