clojurefx
=========

A Clojure JavaFX wrapper. [Documentation and code can be found here](http://zilti.github.io/clojurefx).

Note that this library is brand-new and lacks a lot of features; I'm heavily working at it. Stay tuned!

Lots of neat features will follow soon!

Installation: `[clojurefx "0.0.13"]`

### Short info about the state of the project
Right now I'm rather busy and have a few things to do on a deadline. That's why there's little activity right now. Expect more activity in about one to two months. *I will not abandon this project*. Feel free to add stuff (including tests) and send pull requests - I will review them in about two days and accept them if they're allright.

Overview
--------
ClojureFX essentially is a thin wrapper around JavaFX built using a handful of macros and functions to make the usage
of JavaFX more idiomatic for clojurians. The following macros are provided: `fx, deffx, getfx, swap-content!, 
set-listener!, bind-property! and bidirectional-bind-property!`. To run things on the JavaFX-thread, there's `run-now
and run-later`.

API
---

### [Creating](http://zilti.github.io/clojurefx/#contentcreation)
The no.1 macro for creation is the fx-macro. Additionally, there's the deffx-macro which does the same
as defn, except that it binds the new JavaFX object to a symbol instead of a function.

```clojure
(deffx scn scene :width 800 :height 600 :root rt)
```

* Instead of camelCase, the normal clojure dashes are used for the class names: button, check-box, v-box, ...
* The keys for the options are the normal setters, without the "set" word at the beginning: :title :scene :root and so on.
* **Constructor arguments** also are given that way, e.g. the :width, :height and :root in the example above.
* You can directly add **property bindings**. Give them in a map with the :bind key, example:
  ```clojure
  (fx stage :title "Hello ClojureFX!" :bind {:title title-atom})
  ```

* You can do the same for **action listeners**, just use the :listen key instead:
  ```clojure
  (fx button :text "Hide the window" :listen {:onAction (fn [_] (run-now (.hide stg)))})
  ```

* And you can do it for **child elements**. Use the key `content` or `children` (equivalent). The value of this key must be a datastructure a function given to `swap-content!` would return.

### Modifying
#### [Child elements](http://zilti.github.io/clojurefx/#contentmodification)
Besides the possibility to use the normal Java methods, you can use the `swap-content!` multimethod to modify child-elements.
The return value of the function you provide becomes the new content of the node.

This works for all layout classes as well as everything with child elements, like combo-box, menu, split-pane and so on.

Additionally, there are the helper functions `fx-conj!`, `fx-remove!` and `fx-remove-all!`.

Note that for split-pane and table-view you get maps; See the source code for details (bottom, "Class-specific wrappers").
#### [Properties](http://zilti.github.io/clojurefx/#databinding)
Currently the only way to modify properties this library provides is using the `bind-property!` function.
It expects an atom it will listen to, and whenever you change the atom value, this value will be propagated to the property.

Example:

```clojure
(bind-property! scn :title title-atom)
```

It is also possible to bind multiple properties at once in bind-property!. Just use additional named arguments:

```clojure
(bind-property! scn :width width-atom :height height-atom)
```

Other STM objects will follow.

### Retrieving
To retrieve data from objects in a more idiomatic way, there's the `getfx`-macro:

```clojure
(getfx btn :text) ;; instead of (.getText btn)
(getfx btn :armed?) ;; instead of (.isArmed btn)
```

### [Acting](http://zilti.github.io/clojurefx/#events)
Event handling is really simple. All you need is the action name and a function. Example:

```clojure
(set-listener! btn :on-action [x] (run-now (.hide stg)))
```

Note that your function will get a map. See the source code for further details.

### Adding support for custom JavaFX elements
To add support for element collections like JideFX and ControlsFX is simple:

 * Add the class names to the `pkgs` atom at the beginning of your program. The package name as a String is the key,
   the value is a quoted vector of symbols where each symbol names a class. Note that these names will automatically
   be camelcased, e.g. `separator-menu-item` in that vector will become `SeparatorMenuItem`.
 * You'll probably want to add methods to the multimethods `preprocess-event`, `swap-content!`, `wrap-arg` and `construct-node`.
