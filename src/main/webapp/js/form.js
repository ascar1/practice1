$(document).ready(function () {

    $("#orgAll").click(function () {
        $.ajax({
            url:"/organization/all",
            type:"GET",
            contentType:"application/json; charset=utf-8",
            dataType:"text",
            success: function(result){
                console.log(result);
                alert(result);
            }
        });
    });

    $("#orgFilter").click(function () {
            console.log('id');
            $.ajax({
                url:"/organization/",
                type:"GET",
                data:{'id':$("#OrgID").val()},
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(JSON.stringify(result));
                }
            });
    });

    $("#orgList").click(function () {
                console.log('Org Filter');
                var orgFilter = {
                    name: $("#OrgName").val(),
                    inn:  $("#OrgINN").val(),
                    is_active: $("#OrgIsActive").val()
                };
                console.log('filter', orgFilter);
            $.ajax({
                url:"/organization/list",
                type:"POST",
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                data: JSON.stringify(orgFilter),
                success: function(result){
                    console.log(result);
                    alert(JSON.stringify(result));
                }
            });
    });

    $("#orgSave").click(function () {
        console.log('Org save');
        var OrgSave = {
            name: $("#Org_Name").val(),
            full_name: $("#OrgFullName").val(),
            inn: $("#Org_INN").val(),
            kpp: $("#OrgKPP").val(),
            address: $("#OrgAddress").val(),
            phone: $("#OrgPhone").val(),
            is_active: $("#Org_IsActive").val()
        };
        console.log('PER', OrgSave);
        $.ajax({
            url:"/organization/save",
            type:"POST",
            data: JSON.stringify(OrgSave),
            contentType:"application/json; charset=utf-8",
            success: function(){
                alert('Success');
            }
        });
        clearFields();
    });

    $("#orgUpd").click(function (){
        console.log('Update save');
        var OrgUpdate = {
            id: $("#Org_ID").val(),
            name: $("#Org_Name").val(),
            full_name: $("#OrgFullName").val(),
            inn: $("#Org_INN").val(),
            kpp: $("#OrgKPP").val(),
            address: $("#OrgAddress").val(),
            phone: $("#OrgPhone").val(),
            is_active: $("#Org_IsActive").val()
        };
        $.ajax({
            url:"/organization/update",
            type:"POST",
            data: JSON.stringify(OrgUpdate),
            contentType:"application/json; charset=utf-8",
            success: function(){
                alert('Success');
            }
        });
    });

    $("#OfficeAll").click(function () {
        $.ajax({
            url:"/office/all",
            type:"GET",
            contentType:"application/json; charset=utf-8",
            dataType:"text",
            success: function(result){
                console.log(result);
                alert(result);
            }
        });
    });

    $("#OfficeList").click(function () {
                console.log('Org Filter');
                var orgFilter = {
                    org_id:    $("#Office_ID").val(),
                    name:     $("#Office_Name").val(),
                    phone:    $("#Office_Phone").val(),
                    is_active: $("#Office_IsActive").val()
                };
                console.log('filter', orgFilter);
            $.ajax({
                url:"/office/list",
                type:"POST",
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                data: JSON.stringify(orgFilter),
                success: function(result){
                    console.log(result);
                    alert(JSON.stringify(result));
                }
            });
    });

    $("#officeFilterID").click(function () {
           console.log('id');
                $.ajax({
                    url:"/office/",
                    type:"GET",
                    data:{'id':$("#OfficeID").val()},
                    contentType:"application/json; charset=utf-8",
                    success: function(result){
                        console.log(result);
                        alert(JSON.stringify(result));
                    }
           });
    });

    $("#OfficeSave").click(function () {
        console.log('Org save');
        var OrgSave = {
            org_id: $("#Office_OrgID").val(),
            name: $("#OfficeName").val(),
            address: $("#Office_Address").val(),
            phone: $("#OfficePhone").val(),
            is_active: $("#OfficeIsActive").val()
        };
        console.log('PER', OrgSave);
        $.ajax({
            url:"/office/save",
            type:"POST",
            data: JSON.stringify(OrgSave),
            contentType:"application/json; charset=utf-8",
            success: function(){
                alert('Success');
            }
        });
        clearFields();
    });

    $("#OfficeUpdate").click(function (){
        console.log('Update save');
        var OrgUpdate = {
            id: $("#OfficeID").val(),
            org_id: $("#Office_OrgID").val(),
            name: $("#OfficeName").val(),
            address: $("#Office_Address").val(),
            phone: $("#OfficePhone").val(),
            is_active: $("#OfficeIsActive").val()
        };
        $.ajax({
            url:"/office/update",
            type:"POST",
            data: JSON.stringify(OrgUpdate),
            contentType:"application/json; charset=utf-8",
            success: function(){
                alert('Success');
            }
        });
    });

    $("#UserAll").click(function () {
        $.ajax({
            url:"/user/all",
            type:"GET",
            contentType:"application/json; charset=utf-8",
            dataType:"text",
            success: function(result){
                console.log(result);
                alert(result);
            }
        });
    });

    $("#FilterID").click(function () {
           console.log('id');
                $.ajax({
                    url:"/user",
                    type:"GET",
                    data:{'id':$("#user_id").val()},
                    contentType:"application/json; charset=utf-8",
                    success: function(result){
                        console.log(result);
                        alert(JSON.stringify(result));
                    }
           });
    });

    $("#UserListFilter").click(function () {
                console.log('Org Filter');
                var orgFilter = {
                    office_id:        $("#user_officeId").val(),
                    first_name:       $("#user_first_name").val(),
                    second_name:      $("#user_second_name").val(),
                    middle_name:      $("#user_middle_name").val(),
                    position:         $("#user_position").val(),
                    doc_code:         $("#user_doc_code").val(),
                    citizenship_code: $("#user_citizenship_code").val()
                };
                console.log('filter', orgFilter);
            $.ajax({
                url:"/user/list",
                type:"POST",
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                data: JSON.stringify(orgFilter),
                success: function(result){
                    console.log(result);
                    alert(JSON.stringify(result));
                }
            });
    });

    $("#UserUpdate").click(function (){
        console.log('Update save');
        var OrgUpdate = {
                id:          $("#userId").val(),
                first_name:  $("#userFirst_name").val(),
                second_name: $("#userSecond_name").val(),
                middle_name: $("#userMiddle_name").val(),
                position:    $("#userPosition").val(),
                phone:       $("#userPhone").val(),
                doc_Code:    $("#userDocCode").val(),
                doc_Number:  $("#userDocNumber").val(),
                docDate :    $("#user_doc_date").val(),
                citizenship_code: $("#userCitizenshipCode").val(),
                isIdentified:     $("#user_isIdentified").val()
        };
        $.ajax({
            url:"/user/update",
            type:"POST",
            data: JSON.stringify(OrgUpdate),
            contentType:"application/json; charset=utf-8",
            success: function(){
                alert('Success');
            }
        });
    });

    $("#UserSave").click(function () {
        console.log('Org save');
        var OrgSave = {
                first_name:  $("#userFirst_name").val(),
                second_name: $("#userSecond_name").val(),
                middle_name: $("#userMiddle_name").val(),
                position:    $("#userPosition").val(),
                phone:       $("#userPhone").val(),
                doc_Code:    $("#userDocCode").val(),
                doc_Number:  $("#userDocNumber").val(),
                docDate :    $("#user_doc_date").val(),
                citizenship_code: $("#userCitizenshipCode").val(),
                isIdentified:     $("#user_isIdentified").val()
        };
        console.log('PER', OrgSave);
        $.ajax({
            url:"/user/save",
            type:"POST",
            data: JSON.stringify(OrgSave),
            contentType:"application/json; charset=utf-8",
            success: function(){
                alert('Success');
            }
        });
        clearFields();
    });

});

var clearFields = function () {
    $("#name").val('');
    $("#age").val('');
};