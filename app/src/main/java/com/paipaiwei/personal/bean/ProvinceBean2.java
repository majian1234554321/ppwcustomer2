package com.paipaiwei.personal.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class ProvinceBean2 implements IPickerViewData {


    /**
     * code : 110000
     * name : 北京市
     * node : [{"code":"110100","name":"市辖区","node":[{"code":"110101","name":"东城区","node":[],"parent":"110100"},{"code":"110102","name":"西城区","node":[],"parent":"110100"},{"code":"110105","name":"朝阳区","node":[],"parent":"110100"},{"code":"110106","name":"丰台区","node":[],"parent":"110100"},{"code":"110107","name":"石景山区","node":[],"parent":"110100"},{"code":"110108","name":"海淀区","node":[],"parent":"110100"},{"code":"110109","name":"门头沟区","node":[],"parent":"110100"},{"code":"110111","name":"房山区","node":[],"parent":"110100"},{"code":"110112","name":"通州区","node":[],"parent":"110100"},{"code":"110113","name":"顺义区","node":[],"parent":"110100"},{"code":"110114","name":"昌平区","node":[],"parent":"110100"},{"code":"110115","name":"大兴区","node":[],"parent":"110100"},{"code":"110116","name":"怀柔区","node":[],"parent":"110100"},{"code":"110117","name":"平谷区","node":[],"parent":"110100"},{"code":"110118","name":"密云区","node":[],"parent":"110100"},{"code":"110119","name":"延庆区","node":[],"parent":"110100"}],"parent":"110000"}]
     */



    private String code;
    private String name;
    private List<NodeBeanX> node;

    @Override
    public String getPickerViewText() {
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NodeBeanX> getNode() {
        return node;
    }

    public void setNode(List<NodeBeanX> node) {
        this.node = node;
    }

    public static class NodeBeanX {
        /**
         * code : 110100

         */

        private String code;
        private String name;
        private String parent;
        private List<NodeBean> node;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public List<NodeBean> getNode() {
            return node;
        }

        public void setNode(List<NodeBean> node) {
            this.node = node;
        }

        public static class NodeBean {
            /**
             * code : 110101
             * name : 东城区
             * node : []
             * parent : 110100
             */

            private String code;
            private String name;
            private String parent;
            private List<?> node;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParent() {
                return parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }

            public List<?> getNode() {
                return node;
            }

            public void setNode(List<?> node) {
                this.node = node;
            }
        }
    }
}