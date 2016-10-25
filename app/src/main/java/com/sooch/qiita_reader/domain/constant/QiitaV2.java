package com.sooch.qiita_reader.domain.constant;

/**
 * Qiita APIv2 パラメータの定義.
 * Created by Takashi Sou on 2016/09/23.
 */

public class QiitaV2 {

    public static class Tags {

        public enum Sort {
            /** 投稿数順 */
            COUNT("count"),
            /** 名前順 */
            NAME("name"),
            ;
            public final String sort;
            Sort(String sort) {
                this.sort = sort;
            }

            public String getSort() {
                return sort;
            }
        }
    }

    // TODO
    public static class Items {

    }
}
