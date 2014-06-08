(ns fake.core)


(def header "<html><body>")
(def footer "</body></html")


(defn create-url
  "Create a relative link to an internal page"
  [suffix]
  (str "page" suffix ".html"))


(defn create-paths
  "Create N sequential paths"
  [^Integer n]
  (for [i (range n)] (create-url i)))


(defn anchor-from
  "Given a URL, return an A tag pointing to that URL"
  [url]
  (str "<a href=\"" url "\">" url "</a>"))


(defn anchors-for-paths
  ""
  [paths]
  (map anchor-from paths))


(defn li-from
  "Wrap some arbitrary content in a LI"
  [h]
  (str "<li>" h "</li>"))


(defn ul-from
  "Wrap a sequence of pieces of arbitrary content as a UL containing LIs"
  [hs]
  (str "<ul>" (apply str (map li-from (doall hs))) "</ul>"))


(defn create-page
  "Write text to file"
  [^String path ^String text]
  (spit path text))


(defn generate
  "Create N interlinked HTML pages in directory file-base, plus an index.html with a single link to the first of the interlinked set"
  [^Integer n ^String file-base body-fn]
  (let [paths (create-paths n)
        anchors (anchors-for-paths paths)
        body (str (body-fn) (ul-from anchors))
        html (str header body footer)]
    (doall (for [p paths] (create-page (str file-base "/" p) html)))
    (create-page (str file-base "/index.html") (str header (first anchors) footer))))
  

