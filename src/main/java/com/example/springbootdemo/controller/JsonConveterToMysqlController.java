package com.example.springbootdemo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/tool")
public class JsonConveterToMysqlController {
    @RequestMapping("/mysql")
    public String getMysqlCreateStatement(String tableName,String tableDescription, String requestJson){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE IF EXISTS `" + tableName + "`;\n" +
                "CREATE TABLE `" + tableName + "`  (\n" +
                "  `id` int(11) NOT NULL,\n");
        Gson gson= new Gson();
        JsonObject jsonObject = gson.fromJson(requestJson,JsonObject.class);
        Set<Map.Entry<String, JsonElement>> set = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : set) {
            if (entry.getValue().toString().indexOf("\"") == 0
                    || entry.getValue().isJsonArray()
                    || entry.getValue().toString().indexOf("{") ==0
                    || entry.getValue().toString().contains("false")
                    || entry.getValue().toString().contains("true")
                    || entry.getValue().toString().length() > 4) {
                stringBuilder.append("`"+entry.getKey()+"`"+" varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,"+"\n");
            }else{
                stringBuilder.append("`"+entry.getKey()+"`"+" int(11) NULL DEFAULT NULL,"+"\n");

            }

        }
        stringBuilder.append("`comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,\n" +
                "  `expected` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,\n" +
                "  PRIMARY KEY (`id`) USING BTREE,\n" +
                "  UNIQUE INDEX `CreateBrandDeposit_id_uindex`(`id`) USING BTREE\n" +
                ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '"+ tableDescription + "' ROW_FORMAT = Compact;\n" +
                "\n" +
                "SET FOREIGN_KEY_CHECKS = 1;");
        String insertValue = insertValueByJson(requestJson,tableName);
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("createTable",stringBuilder.toString());
        jsonObject1.addProperty("insertValue",insertValue);
        return jsonObject1.toString();
    }


    public String insertValueByJson(String json,String tableName){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into "+ tableName +"(id,");
        Gson gson= new Gson();
        JsonObject jsonObject = gson.fromJson(json,JsonObject.class);
        Set<Map.Entry<String, JsonElement>> set = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : set) {
            stringBuilder.append(entry.getKey()+",");
        }
        stringBuilder.append("comment,expected) VALUES(1,");
        for (Map.Entry<String, JsonElement> entry : set) {
            if (entry.getValue().isJsonArray() || entry.getValue().toString().indexOf("{") == 0){
                stringBuilder.append("\'"+entry.getValue()+"\'"+",");
            }else {
                stringBuilder.append(entry.getValue()+",");
            }
        }

        stringBuilder.append("null,null)");
        return stringBuilder.toString();
    }

}
