var app=angular.module("MyBanqueApp",[]);
app.controller("MyBanqueCtrl", function($scope,$http) {
	$scope.compte=null;
	$scope.codeCompte=null;
	$scope.pageOperations=[];
	$scope.pageCourante=0;
	$scope.pageSize=3;
	$scope.pages=[];
	$scope.errorMessage=null;
	$scope.operation={type:"versement", montant:0,compte2:null}
	$scope.chargerCompte=function(){
		$http.get("/getCompte/"+$scope.codeCompte)
		.success(function(data){
			$scope.compte=data;
		})
		.error(function(data){
			$scope.errorMessage=data.message;
		});
	};
	$scope.chargerOperations=function(){
		$scope.pageCourante=0;
		$http.get("/operations?codeCompte="+$scope.codeCompte+"&page="+$scope.pageCourante+"&size="+$scope.pagSize)
		.success(function(data){
			$scope.pageOperations=data;
			$scope.pages=new Array(data.totalPages);
	});
		};
	$scope.goToPage=function(p){
		$scope.pageCourante=p;
		$scope.chargerOperations();
	};
	$scope.saveOperation=function(){
		var params="";
		if($scope.operation.type=='virement'){
			params="cpt1="+$scope.codeCompte+"&cpt2="+$scope.operation.compte2+"&montant="+$scope.operation.montant+"&codeEmp=1";
		}else{
			params="code="+$scope.codeCompte+"&montant="+$scope.operation.montant+"&codeEmp=1";
		}
		$http({
			method:'PUT',
			url: $scope.operation.type,
			data:params,
			headers: {'Content-Type':'application/x-www-form-urlencoded'}
		})
		.success(function(data){
			$scope.chargerCompte();
			
		})
		.error(function(data){
			$scope.errorMessage=data.message;
		});
	};
	
	
});