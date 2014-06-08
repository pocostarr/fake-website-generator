# fake-website-generator

A very simple library. Creates heavily-interlinked sets of HTML for crawler testing.

## Usage

Generate the HTML as follows:

```clojure
(use 'fake.core)
(generate 10 "/tmp/html-base-dir")
```

Copy the files into your webservers' htdoc base directory.

## License

Copyright © 2014 Rory Gibson

Distributed under the Eclipse Public License version 1.0 
