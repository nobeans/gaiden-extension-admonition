filters = {
    admonition {
        before = { text ->
            def converter = { all, type, title, lines ->
                def header = title ? """<div class="admonition-caption">$title</div>""" : ""
                return """<div class="admonition admonition-${type.toLowerCase()}">
                         |  <div class="admonition-icon">
                         |    <i class="fa"></i>
                         |  </div>
                         |  <div class="admonition-content" markdown="1">
                         |    $header
                         |    ${lines.replaceAll(/(?m)^\s*>\s*?/, '')}
                         |  </div>
                         |</div>""".stripMargin()
            }
            text.
                replaceAll(/(?ms)^> \*\*(NOTE|TIP|WARNING|IMPORTANT)\*\*(?::(.*?))?$(.*?)(?:^$|> ----)/, converter). // block
                replaceAll(/(?ms)^\*\*(NOTE|TIP|WARNING|IMPORTANT)\*\*:()(.*?)$/, converter) // inline
        }
    }
}
