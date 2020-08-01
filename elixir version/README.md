# How to run

Elixir should be installed as well as iex (Usually comes with Elixir)
Run the command 
```
iex -S mix
```
In the project directory then type
```
KVServer.accept(4040)
```
4040 can be replaced with whatever port you want. Then have a client connect to it.



# KV

**TODO: Add description**

## Installation

If [available in Hex](https://hex.pm/docs/publish), the package can be installed
by adding `kv` to your list of dependencies in `mix.exs`:

```elixir
def deps do
  [
    {:kv, "~> 0.1.0"}
  ]
end
```

Documentation can be generated with [ExDoc](https://github.com/elixir-lang/ex_doc)
and published on [HexDocs](https://hexdocs.pm). Once published, the docs can
be found at [https://hexdocs.pm/kv](https://hexdocs.pm/kv).

