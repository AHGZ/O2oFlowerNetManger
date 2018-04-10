package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by heguozhong on 2017/12/29/029.
 * 门店分类管理商品Bean类
 */

public class SortManagementBean implements Serializable{


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 1
         * name : 热菜
         * num : 10
         * sort : 1
         */

        private String id;
        private String name;
        private String num;
        private String sort;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", num='" + num + '\'' +
                    ", sort='" + sort + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SortManagementBean{" +
                "list=" + list +
                '}';
    }
}
