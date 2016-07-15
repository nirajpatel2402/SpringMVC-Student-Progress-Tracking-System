<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FlightPlan Administration</title>
<style type="text/css">
.dataTables_filter {
display: none; 
}
.hide{

visibility: hidden

}
</style>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
<script src="<c:url value="/javascript/jquery.js" />" type="text/javascript"></script>
<!-- <script src="http://jquery-datatables-row-reordering.googlecode.com/svn/trunk/media/js/jquery.dataTables.js" type="text/javascript"></script> -->
<script src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js" type="text/javascript"></script>

<script src="<c:url value="/javascript/jquery-ui.js" />" type="text/javascript"></script>
<script src="<c:url value="/javascript/jquery.dataTables.rowReordering.js" />" type="text/javascript"></script>
<script src="<c:url value="/javascript/jquery.dataTables.rowGrouping.js" />" type="text/javascript"></script>
<!-- <script src="http://datatables.net/release-datatables/extensions/ColReorder/js/dataTables.colReorder.js" type="text/javascript"></script> -->
<link rel="stylesheet" type="text/css" href="http://rawgit.com/akottr/dragtable/master/dragtable.css" />
<script src="http://rawgit.com/akottr/dragtable/master/jquery.dragtable.js"></script>


<script type="text/javascript" charset="utf-8">
		    $(document).ready(function () {
		    	
// 		    	$('#mytable').dragtable({
// 		    		dragaccept:'.accept',
// 		    		persistState: function(table) { 
// 		    		var str = "";
// 		    	    table.el.find('th').each(function(i) { 
// 		    	      if(this.id != '') {
// 		    	    	  table.sortOrder[this.id]=i;
// 		    	    	  str += this.id+' ';
// 		    	      }  
// 		    	    }); 
// 		    	    $.ajax({url: "<c:url value='/updateRunwayOrder.html' />", 
// 		                data: {
// 		                    hello: 'world',
// 		                    str: str
// 		                 }
// 		                }); 
// 		    	  } 
// 		    	});  
		    	
		        $('#mytable').dataTable(
		        		{
// 		        			"dom": 'Rlfrtip',
		        		    "bPaginate": false, 
		        		    "bLengthChange": false,
		        		    "bFilter": true,
		        		    "bInfo": false,
		        		    "bAutoWidth": false
		        	        })
                                .rowReordering({ sURL:"<c:url value='/updateStageOrder.html' />", sRequestType: "GET"});
		       
		        $('#cell').dataTable(
		        		{
		        		    "bPaginate": false, 
		        		    "bLengthChange": false,
		        		    "bFilter": true,
		        		    "bInfo": false,
		        		    "bAutoWidth": false })
                                .rowReordering({ sURL:"<c:url value='/updateCheckpointOrder.html' />", sRequestType: "GET"});
		        
		        $(".sortableList").sortable({
		            update: function(event, ui) {
		                $.ajax({
		                    type: "GET",
		                    url:  "<c:url value='/updateCheckpointOrder.html' />",
		                    data: {
		                        "assignmentId" : "${assignment.id}",
		                        "sectionIndex" : "${sectionIndex}",
		                        "str" : ui.item.attr("id"),
		                        "newIndex" : ui.item.index()
		                    }
		                });
		            }
		        });
		    });
		</script>

</head>
<body>
	<h3 align="center" class="well text-center">Golden Eagle Flight
		Plan</h3>

	<div align="center">
		<a href="newstage.html"><h2>Add a stage</a> | <a
			href="newrunway.html">Add a runway</a> | <a href="newcheckpoint.html">Add
			a checkpoint</a>
		</h2>
	</div>
	
	<table id="mytable"  border="1" class="table table-striped" align="center">
	<thead>
		<tr>
			
			<th class="hide"></th>
			<th></th>
			<c:forEach items="${runway}" var="runway">
			<th class="accept" align="center">${runway.description}<a href="editrunway.html?id=${runway.id}"> [Edit]</a></th>
			</c:forEach> 
			
		</tr>
	</thead>
	<tbody>
		
			<c:forEach items="${stage}" var="stage" varStatus="status">
			
				<tr id="${stage.id}" > 
					<td class="hide">${stage.position}</td>  
					<td>${stage.sname}<a href="editstage.html?id=${stage.id}"> [Edit]</a></td> 
					<c:forEach items="${runway}" var="runway">
						<td><c:forEach items="${cell }" var="cell">
						
						<ul id="sortable" class="sortableList"> 
							<c:if test="${cell.stage.id == stage.id && cell.runway.id == runway.id}">
									<c:forEach items="${checkpoints }" var="checkpoints">
										<c:if test="${cell.id == checkpoints.cell.id}">
										
											<li id=${checkpoints.id }><input type="checkbox" name="name"
															value=""> ${checkpoints.description}
															<a href="edit.html?id=${checkpoints.id}">[Edit / Delete]</a><br/></li>
															
										</c:if>
									</c:forEach>
								</c:if>
								
						</ul> 
							</c:forEach>
							</td>
					</c:forEach>
				</tr>
			</c:forEach>
			</tbody>
	</table>

	<div align="left"> 
        <a href="adminhome.html"><h4>Go Back &nbsp;&nbsp;&nbsp;</h4></a>
    </div>

</body>
</html>