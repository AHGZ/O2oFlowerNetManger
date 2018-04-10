package com.huafan.huafano2omanger.base;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.callback.AdapterLoader;
import com.huafan.huafano2omanger.callback.BaseHolder;
import com.huafan.huafano2omanger.callback.OnLoadMoreListener;


/**
 * author: zhangpeisen
 * created on: 2017/10/26 下午5:45
 * description:
 */
public class NewBottomViewHolder<T> extends BaseHolder<T> {
    private final LinearLayout container;
    private final ProgressBar pb;
    private final TextView content;

    public NewBottomViewHolder(View itemView) {

        super(itemView);
        container = (LinearLayout) itemView.findViewById(R.id.footer_container);
        pb = (ProgressBar) itemView.findViewById(R.id.progressbar);
        content = (TextView) itemView.findViewById(R.id.content);
    }

    public void bindDateView(HFWBaseAdapter adapter) {
        final OnLoadMoreListener loadMoreListener = adapter.getLoadMoreListener();
        switch (adapter.loadState) {
            case AdapterLoader.STATE_LASTED:
                pb.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                container.setOnClickListener(null);
                content.setText(R.string.text_no_more);

                break;
            case AdapterLoader.STATE_LOADING:
                content.setVisibility(View.VISIBLE);
                content.setText(R.string.text_load_more);
                container.setOnClickListener(null);
                pb.setVisibility(View.VISIBLE);
                if (loadMoreListener != null) {
                    loadMoreListener.onLoadMore();
                }
                break;
            case AdapterLoader.STATE_ERROR:
                container.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                content.setText(R.string.text_load_error);
                container.setOnClickListener(v -> {
                    if (loadMoreListener != null) {
                        loadMoreListener.onLoadMore();
                    }
                    content.setText(R.string.text_load_more);
                    pb.setVisibility(View.VISIBLE);
                });
                break;
            default:
                break;
        }
    }
}