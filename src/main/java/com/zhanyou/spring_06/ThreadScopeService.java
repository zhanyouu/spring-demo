package com.zhanyou.spring_06;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("threadLocalScope")
@Component
public class ThreadScopeService {
}
