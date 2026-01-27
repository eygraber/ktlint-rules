package com.eygraber.ktlint.rules

import com.pinterest.ktlint.cli.ruleset.core.api.RuleSetProviderV3
import com.pinterest.ktlint.rule.engine.core.api.RuleProvider
import com.pinterest.ktlint.rule.engine.core.api.RuleSetId
import com.eygraber.ktlint.rules.RuleSetId as EygraberRuleSetId

public class EygraberRuleSetProvider : RuleSetProviderV3(
  id = RuleSetId(EygraberRuleSetId.VALUE),
) {
  override fun getRuleProviders(): Set<RuleProvider> = setOf(
    RuleProvider { NoWhitespaceAfterKeyword() },
    RuleProvider { NewlineForMultilineKeyword() },
  )
}
