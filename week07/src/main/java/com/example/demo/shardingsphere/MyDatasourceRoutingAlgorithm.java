/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.shardingsphere<br/>
 * <b>文件名：</b>MyDatasourceRoutingAlgorithm.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 *//*

package com.example.demo.shardingsphere;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.HashSet;

*/
/**
 * <b>类  名：</b>MyDatasourceRoutingAlgorithm<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 *//*

@Slf4j
public class MyDatasourceRoutingAlgorithm  implements HintShardingAlgorithm<String> {

    */
/**
     * 用户数据源
     *//*

    private static final String DS_USER = "ds1";

    */
/**
     * 订单数据源
     *//*

    private static final String DS_ORDER = "ds2";

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue)
    {
        Collection<String> result = new HashSet<>();
        for(String value : shardingValue.getValues())
        {
            if(DatasourceType.DATASOURCE_USER.toString().equals(value))
            {
                if(availableTargetNames.contains(DS_USER))
                {
                    result.add(DS_USER);
                }
            }
            else
            {
                if(availableTargetNames.contains(DS_ORDER))
                {
                    result.add(DS_ORDER);
                }
            }
        }
        log.info("availableTargetNames:{},shardingValue:{},返回的数据源:{}",
                new Object[] { availableTargetNames, shardingValue, result });

        return result;
    }
}
*/
