package com.huafan.huafano2omanger.view.fragment.shop.teamexamine;

import com.huafan.huafano2omanger.entity.TeamExamineBean;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 团队核销视图层
 */

public interface ITeamExamineView {
    void onError(String errorMsg);

    void onSuccess(TeamExamineBean teamExamineBean);

    void onSuccess(String message);

    void showDialog();

    void hideDialog();
}
