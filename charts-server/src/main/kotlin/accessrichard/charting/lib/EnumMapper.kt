package accessrichard.charting.lib

inline fun <reified T : Enum<T>> enumValueOfIgnoreCase(name: String): T =
        enumValues<T>().firstOrNull { it.name.equals(name, ignoreCase = true) }
                ?: throw Exception("Invalid value $name, valid values: ${enumValues<T>().map { it }.joinToString(", ")}.")

inline fun <reified T : Enum<T>> enumValueOfIgnoreCaseOrDefault(name: String, default: T): T =
        enumValues<T>().firstOrNull { it.name.equals(name, ignoreCase = true) }
                ?: default