
desc "publish to github pages"
task :publish => :build do
  `git push github HEAD:gh-pages`
end

desc "package go"
task :go do
  `pygmentize -f html -l go -O full -o go/index.html go/bike.go`
end

desc "package clojure"
task :clojure do
  `pygmentize -f html -l clojure -O full -o clojure/index.html clojure/bike.clj`
end

desc "package java"
task :java do
  `pygmentize -f html -l java -O full -o java/index.html java/BikeAcrossEurope.java`
end

desc "package ruby"
task :ruby do
  `pygmentize -f html -l ruby -O full -o ruby/index.html ruby/bike.rb`
end

desc "package python"
task :python do
  `pygmentize -f html -l python -O full -o python/index.html python/bike.py`
end

desc "package javascript"
task :javascript do
  `pygmentize -f html -l javascript -O full -o javascript/index.html javascript/bike.js`
end

desc "build all languages"
task :build => [:java,
                :clojure,
                :go,
                :python,
                :ruby,
                :javascript]

task :default => :build
