mvn -clean -Denv=dev -Dmaven.test.skip=true package -e

scp traget/test.war kingva@58.210.101.202:59102