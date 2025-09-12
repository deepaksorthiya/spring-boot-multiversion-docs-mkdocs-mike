---
tags:
  - guide
  - beginner
---

# Welcome to Guides Page

I am version 3.0.0 Guides page
For full documentation visit [mkdocs.org](https://www.mkdocs.org).

## Expressing branches as Uni operations

Suppose that we have a pipeline where a `Uni` is created from a random value, and suppose that we want to have a
different processing pipeline depending on whether the value is odd or even.
Let's have these 2 `Uni`-returning methods to model different behaviors:

```java linenums="1"
{{insert('java/com/example/SpringBootGettingStartApplication.java','main') }}
```

## Commands

* `mkdocs new [dir-name]` - Create a new project.
* `mkdocs serve` - Start the live-reloading docs server.
* `mkdocs build` - Build the documentation site.
* `mkdocs -h` - Print help message and exit.

## Project layout

    mkdocs.yml    # The configuration file.
    docs/
        index.md  # The documentation homepage.
        ...       # Other markdown pages, images and other files.
