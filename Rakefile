
desc "publish to github pages"
task :publish => :build do
  `git push github HEAD:gh-pages`
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
                :python,
                :ruby,
                :javascript]
