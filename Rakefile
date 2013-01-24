
desc "publish to github pages"
task :publish do
  `git push github HEAD:gh-pages`
end

desc "package java"
task :java do
  `tar czf java/project.tgz java/.idea java/java.iml java/src`
end

task :ruby do
  `pygmentize -f html -l ruby -O full -o ruby/index.html ruby/bike.rb`
end

task :python do
  `pygmentize -f html -l python -O full -o python/index.html python/bike.py`
end

task :javascript do
  `pygmentize -f html -l javascript -O full -o javascript/index.html javascript/bike.js`
end

task :build => [:java,
                :python,
                :ruby,
                :javascript]
